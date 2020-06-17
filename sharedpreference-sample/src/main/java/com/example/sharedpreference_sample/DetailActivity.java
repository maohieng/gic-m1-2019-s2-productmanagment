package com.example.sharedpreference_sample;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPrefs.putString(this, "token", "this is new token");

        String token = SharedPrefs.getString(this, "token");
        Log.i(TAG, "onCreate: " + token);
    }
}
