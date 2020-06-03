package com.example.broadcasts_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    public static class DownloadMusicReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int downloadProgress = intent.getIntExtra(DownloadMusicService.DOWNLOAD_PERCENTAGE, -1);
            if (downloadProgress == -1)
                return;

            if (downloadProgress == 0) {
                Toast.makeText(context, "Start downloading...", Toast.LENGTH_SHORT).show();
            } else if (downloadProgress == 100) {
                Toast.makeText(context, "Download completed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Downloading (" + downloadProgress + "%)...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class ConnectivityChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // create a connectivity manager
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            // get connectivity information
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static final String ACTION_RECEIVE_SMS = "android.provider.telephony.SMS_RECIEVED";

    public static class SMSReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null &&
                    intent.getAction().equals(ACTION_RECEIVE_SMS)) {

                // Expand execution in onReceive for 10 seconds more
                PendingResult pendingResult = goAsync();
                new Task(context, pendingResult).execute(intent);
            }
        }

        public static class Task extends AsyncTask<Intent, Integer, String> {

            Context context;
            PendingResult pendingResult;

            Task(Context context, PendingResult pendingResult) {
                this.context = context;
                this.pendingResult = pendingResult;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(context, "Start processing SMS.", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Intent... intents) {
                Bundle bundle = intents[0].getExtras();
                if (bundle != null) {
                    // get sms objects
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    if (pdus.length == 0) {
                        return null;
                    }

                    // large message might be broken into many
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < pdus.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        sb.append(messages[i].getMessageBody());
                    }

                    // String sender = messages[0].getOriginatingAddress();
                    return sb.toString();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                Toast.makeText(context, "SMS: " + message, Toast.LENGTH_SHORT).show();
                // Must call finish() for BroadcastReceiver can be recycled.
                pendingResult.finish();
            }
        }
    }

    EditText editCommand;
    LocalBroadcastManager broadcastManager;
    DownloadMusicReceiver downloadMusicReceiver;
    ConnectivityChangeReceiver connectivityChangeReceiver;
    SMSReceiver smsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editCommand = findViewById(R.id.editText);
        broadcastManager = LocalBroadcastManager.getInstance(this);
        downloadMusicReceiver = new DownloadMusicReceiver();
        connectivityChangeReceiver = new ConnectivityChangeReceiver();
        smsReceiver = new SMSReceiver();

        // Grant SMS permission
    }

    public void clickSubmit(View view) {
        String musicUrl = editCommand.getText().toString();
        // start download service
        Intent intent = new Intent(this, DownloadMusicService.class);
        intent.putExtra("MUSIC_URL", musicUrl);
        startService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register download progress
        IntentFilter intentFilter = new IntentFilter(DownloadMusicService.ACTION_DOWNLOAD_STATUS);
        broadcastManager.registerReceiver(downloadMusicReceiver, intentFilter);

        // Register connectivity changes as Context-registered receiver
        IntentFilter connectionFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(connectivityChangeReceiver, connectionFilter);

//        IntentFilter intentFilter1 = new IntentFilter(DownloadMusicService.ACTION_DOWNLOAD_STATUS);
//        this.registerReceiver(downloadMusicReceiver, intentFilter1,
//                Manifest.permission.READ_EXTERNAL_STORAGE, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register receiver for SMS
        IntentFilter smsFilter = new IntentFilter(ACTION_RECEIVE_SMS);
        this.registerReceiver(smsReceiver, smsFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // unregister receiving sms
        this.unregisterReceiver(smsReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // unregister download progress broadcasting
        broadcastManager.unregisterReceiver(downloadMusicReceiver);

        // unregister connectivity change
        this.unregisterReceiver(connectivityChangeReceiver);
    }
}
