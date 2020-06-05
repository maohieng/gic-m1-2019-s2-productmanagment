package com.example.notificationsample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickShowBasicNotification(View view) {
        showNotification();
    }

    private static final int BASIC_NOTIF_ID = 1;
    private static final String BASIC_NOTIF_CHANNEL_ID = "basic-channel-id";

    private void showNotification() {
        // Create a basic notification

        // 1. Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(BASIC_NOTIF_CHANNEL_ID, BASIC_NOTIF_CHANNEL_ID,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Describe the basic notification channel ID");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        String notifMsg = "Hello there,\n" +
                "This is my first basic notification.";

        // 2. Create a notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, BASIC_NOTIF_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_audiotrack_white_24dp) /*required*/
                .setContentTitle(getString(R.string.app_name)) /*required*/
                .setContentText(notifMsg) /*required*/
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notifMsg));

        // 3. Set the notification's tab action
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        builder = builder.setContentIntent(pendingIntent)
                .setAutoCancel(true); // automatically removes the notification when the user taps it.

        // 4. Show notification
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(BASIC_NOTIF_ID, builder.build());
    }
}
