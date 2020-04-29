package edu.itc.gic.m1.services_sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
    }

    public void clickSubmitCommand(View view) {
        String command = editText.getText().toString();
        Intent intent = new Intent(this, HelloForgroundService.class);
        intent.putExtra("command", command);
        startService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop HelloService when this activity is destroyed.
//        Intent intent = new Intent(this, HelloService.class);
//        stopService(intent);
    }

//    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo runningService : manager.getRunningServices(100)) {
//            if (serviceClass.getName().equals(runningService.service.getClassName())) {
//                return true;
//            }
//        }
//
//        return false;
//    }
}
