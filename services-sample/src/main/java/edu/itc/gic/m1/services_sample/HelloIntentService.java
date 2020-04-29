package edu.itc.gic.m1.services_sample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 4/29/2020
 */
public class HelloIntentService extends IntentService {

    public static final String TAG = "HelloIntentService";

    public HelloIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final String command = intent.getStringExtra("command");
        Log.i(TAG, "onHandleIntent: start process: " + command);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "run: process " + command + " has been done.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: Goodbye!");
        Toast.makeText(this, "Goodbye!", Toast.LENGTH_SHORT).show();
    }
}
