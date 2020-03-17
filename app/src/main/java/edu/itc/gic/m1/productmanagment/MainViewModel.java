package edu.itc.gic.m1.productmanagment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 3/11/2020
 */
public class MainViewModel extends AndroidViewModel {

//    LiveData<List<Product>> productsObservable;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

//    public void loadProduct() {
//        productsObservable = productDao.loadAll();
//    }
}
