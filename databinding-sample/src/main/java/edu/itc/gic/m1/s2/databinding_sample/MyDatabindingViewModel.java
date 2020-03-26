package edu.itc.gic.m1.s2.databinding_sample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 3/18/2020
 */
public class MyDatabindingViewModel extends AndroidViewModel {

    // Data
    private User user = new User();

    private LiveData<Integer> voteNumber = new MutableLiveData<>(0); // initial value

    private ObservableInt voteNumber2 = new ObservableInt(0);

    public MyDatabindingViewModel(@NonNull Application application) {
        super(application);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Click Handlers
    ///////////////////////////////////////////////////////////////////////////

    public void clickVoteMe() {
//        Integer value = voteNumberObservable.getValue();
//        voteNumberObservable.setValue(++value);

        int value = voteNumber2.get();
        voteNumber2.set(++value);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getters
    ///////////////////////////////////////////////////////////////////////////


    public User getUser() {
        return user;
    }

    public LiveData<Integer> getVoteNumber() {
        return voteNumber;
    }

    public ObservableInt getVoteNumber2() {
        return voteNumber2;
    }
}
