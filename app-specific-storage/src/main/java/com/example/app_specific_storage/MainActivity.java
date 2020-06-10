package com.example.app_specific_storage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_NAME_SAMPLE = "sampleText.txt";

    TextView textListFile, textWriteFile, textReadFile;
    InternalStorageHelper internalStorageHelper;

    TextView textListFileE, textWriteFileE, textReadFileE;
    ExternalStorageHelper externalStorageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textListFile = findViewById(R.id.textListFile);
        textWriteFile = findViewById(R.id.editTextTextMultiLine);
        textReadFile = findViewById(R.id.textReadText);

        internalStorageHelper = new InternalStorageHelper(getApplicationContext());

        textListFile.setText(internalStorageHelper.listFiles().toString());
        try {
            String readTextFile = internalStorageHelper.readTextFile(FILE_NAME_SAMPLE);

            textWriteFile.setText(readTextFile);
            textReadFile.setText(readTextFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        textListFileE = findViewById(R.id.textListFileE);
        textWriteFileE = findViewById(R.id.editTextTextMultiLineE);
        textReadFileE = findViewById(R.id.textReadTextE);

        externalStorageHelper = new ExternalStorageHelper(getApplicationContext());

        textListFileE.setText(externalStorageHelper.listFiles().toString());
        try {
            String readTextFile = externalStorageHelper.readTextFile(FILE_NAME_SAMPLE);

            textWriteFileE.setText(readTextFile);
            textReadFileE.setText(readTextFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickSaveInternalTextFile(View view) {
        // Hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(textWriteFile.getWindowToken(), 0);

        // Get writing content
        String writeContent = textWriteFile.getText().toString();
        try {
            // Save into file
            internalStorageHelper.writeTextFile(FILE_NAME_SAMPLE, writeContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set read text for realtime update :D
        textReadFile.setText(writeContent);
    }

    public void clickSaveExternalTextFile(View view) {
        // Hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(textWriteFileE.getWindowToken(), 0);

        // Get writing content
        String writeContent = textWriteFileE.getText().toString();
        try {
            // Save into file
            externalStorageHelper.writeTextFile(FILE_NAME_SAMPLE, writeContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set read text for realtime update :D
        textReadFileE.setText(writeContent);
    }
}
