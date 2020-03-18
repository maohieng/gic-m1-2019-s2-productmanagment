package edu.itc.gic.m1.s2.databinding_sample;

import android.app.Application;
import android.database.Observable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 3/18/2020
 */
public class MyDatabindingViewModel extends AndroidViewModel {

    // Data
    private String name = "MAO Hieng";
    private String email = "maohieng@gmail.com";

    private MutableLiveData<Integer> voteNumberObservable = new MutableLiveData<>(0);

    public MyDatabindingViewModel(@NonNull Application application) {
        super(application);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Click Handlers
    ///////////////////////////////////////////////////////////////////////////

    public void clickVoteMe() {
        Integer value = voteNumberObservable.getValue();
        voteNumberObservable.setValue(++value);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getters
    ///////////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LiveData<Integer> getVoteNumber() {
        return voteNumberObservable;
    }
}
