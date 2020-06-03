package com.example.broadcasts_sample;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class DownloadMusicService extends IntentService {

    public static final String TAG = DownloadMusicService.class.getSimpleName();

    /**
     * Broadcast identifier
     */
    public static final String ACTION_DOWNLOAD_STATUS = "com.example.broadcasts_sample.action.DOWNLOAD_PROGRESS";

    public static final String DOWNLOAD_PERCENTAGE = "DOWNLOAD_PERCENTAGE";

    LocalBroadcastManager broadcastManager;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public DownloadMusicService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize
        broadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // do your work here
        String musicUrl = intent.getStringExtra("MUSIC_URL");

//        Toast.makeText(this, "Start downloading " + musicUrl, Toast.LENGTH_SHORT).show();
        Intent progressIntent = new Intent(ACTION_DOWNLOAD_STATUS);
        progressIntent.putExtra(DOWNLOAD_PERCENTAGE, 0);
        broadcastManager.sendBroadcast(progressIntent);

//        this.sendBroadcast(intent, Manifest.permission.READ_EXTERNAL_STORAGE);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Toast.makeText(this, "Downloading (25%) ...", Toast.LENGTH_SHORT).show();
        progressIntent.putExtra(DOWNLOAD_PERCENTAGE, 25);
        broadcastManager.sendBroadcast(progressIntent);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Toast.makeText(this, "Downloading (50%) ...", Toast.LENGTH_SHORT).show();
        progressIntent.putExtra(DOWNLOAD_PERCENTAGE, 50);
        broadcastManager.sendBroadcast(progressIntent);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Toast.makeText(this, "Downloading (75%) ...", Toast.LENGTH_SHORT).show();
        progressIntent.putExtra(DOWNLOAD_PERCENTAGE, 75);
        broadcastManager.sendBroadcast(progressIntent);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Toast.makeText(this, "Downloading completed.", Toast.LENGTH_SHORT).show();
        progressIntent.putExtra(DOWNLOAD_PERCENTAGE, 100);
        broadcastManager.sendBroadcast(progressIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Goodbye", Toast.LENGTH_SHORT).show();
    }
}
