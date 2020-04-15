package edu.itc.gic.m1.services_sample;

import android.app.Service;
import android.content.Intent;
import android.media.AsyncPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 4/15/2020
 */
public class HelloService extends Service {

    AsyncPlayer playerThread;

    @Override
    public void onCreate() {
        super.onCreate();
        // initial resource, thread, receiver
        playerThread = new AsyncPlayer("HelloService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String command = intent.getStringExtra("command");
        Toast.makeText(this, "Command received: " + command, Toast.LENGTH_SHORT).show();

        if (command != null && command.equals("exit")) {
            stopSelf();
        }

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
        Toast.makeText(this, "Goodbye.", Toast.LENGTH_SHORT).show();
        // clean up resources
        playerThread = null;
    }
}
