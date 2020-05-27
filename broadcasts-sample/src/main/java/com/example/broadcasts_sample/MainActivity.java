package com.example.broadcasts_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
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
                Toast.makeText(context, String.format("Downloading (" + downloadProgress + "%)..."), Toast.LENGTH_SHORT).show();
            }
        }
    }

    EditText editCommand;
    LocalBroadcastManager broadcastManager;
    DownloadMusicReceiver downloadMusicReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editCommand = findViewById(R.id.editText);
        broadcastManager = LocalBroadcastManager.getInstance(this);
        downloadMusicReceiver = new DownloadMusicReceiver();
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
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(DownloadMusicService.ACTION_DOWNLOAD_STATUS);
        broadcastManager.registerReceiver(downloadMusicReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // unregister download progress broadcasting
        broadcastManager.unregisterReceiver(downloadMusicReceiver);
    }
}
