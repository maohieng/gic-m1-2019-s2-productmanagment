package com.example.sharedpreference_sample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_FIRST_START = "firstStart";

    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = this.getSharedPreferences(getString(R.string.sharedpref_sample_filename),
                Context.MODE_PRIVATE);

        // Read data from sharedpreference
        boolean isFirstStart = mSharedPreferences.getBoolean(PREF_FIRST_START, true);

        if (isFirstStart) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this)
                    .setTitle("Welcome")
                    .setMessage("Welcome to our application.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Save sharedpreference
                            SharedPreferences.Editor edit = mSharedPreferences.edit()
                                    .putBoolean(PREF_FIRST_START, false);
                            edit.apply();
                        }
                    });

            alertBuilder.show();
        }
    }

    public void clickStartDetail(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}

