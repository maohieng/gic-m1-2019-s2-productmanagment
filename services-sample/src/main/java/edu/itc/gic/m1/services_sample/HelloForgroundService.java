package edu.itc.gic.m1.services_sample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 4/29/2020
 */
public class HelloForgroundService extends Service {

    public static final int FOREGROUND_ID = 12;
    public static final String FOREGROUND_CHANNEL_ID = "play-music-channel-id";

    @Override
    public void onCreate() {
        super.onCreate();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(FOREGROUND_CHANNEL_ID, "Sample Service Music Player",
                    NotificationManager.IMPORTANCE_LOW);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, FOREGROUND_CHANNEL_ID)
                .setContentTitle("Sample Service Music Player")
                .setContentText("Your music is playing")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build();

        startForeground(FOREGROUND_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String command = intent.getStringExtra("command");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (command != null) {
                    if (command.equals("exit")) {
                        stopSelf();
                    } else if (command.equals("stop")) {
                        stopForeground(true);
                    }
                }
            }
        }, 2000);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Goodbye", Toast.LENGTH_SHORT).show();
    }
}
