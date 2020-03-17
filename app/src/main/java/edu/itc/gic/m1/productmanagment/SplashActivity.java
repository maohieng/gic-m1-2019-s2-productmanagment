package edu.itc.gic.m1.productmanagment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 3/11/2020
 */
public class SplashActivity extends AppCompatActivity {

    TextView textView;
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.textView);

        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        splashViewModel.countDownObserver.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                if (value) {
                    // start MainActivity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);

                    // finish itself
                    finish();
                }
            }
        });

        splashViewModel.countDownTime.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer left) {
                textView.setText("Timeleft: " + left);
            }
        });
    }
}
