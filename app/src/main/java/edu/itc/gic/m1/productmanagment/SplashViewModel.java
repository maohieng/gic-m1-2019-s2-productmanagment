package edu.itc.gic.m1.productmanagment;

import android.app.Application;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 3/11/2020
 */
public class SplashViewModel extends AndroidViewModel {

    final MutableLiveData<Boolean> countDownObserver = new MutableLiveData<>();
    final MutableLiveData<Integer> countDownTime = new MutableLiveData<>();

    public SplashViewModel(@NonNull Application application) {
        super(application);

        countDownTime.setValue(0);
        countDownObserver.setValue(false);
        CountDownTimer counter = new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long l) {
                Log.i("SplashViewModel", "Tick: " + l);

                int timeLeft = (int) ((5000 - l) / 1000);
                countDownTime.setValue(timeLeft);
            }

            @Override
            public void onFinish() {
                countDownObserver.setValue(true);
            }
        };
        counter.start();
    }
}
