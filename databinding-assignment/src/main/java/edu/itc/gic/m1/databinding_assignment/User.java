package edu.itc.gic.m1.databinding_assignment;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 3/26/2020
 */
public class User {

    private ObservableField<String> name = new ObservableField<>();
    private ObservableInt age = new ObservableInt();

    ///////////////////////////////////////////////////////////////////////////
    // Getters
    ///////////////////////////////////////////////////////////////////////////

    public ObservableField<String> getName() {
        return name;
    }

    public ObservableInt getAge() {
        return age;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Setters
    ///////////////////////////////////////////////////////////////////////////


    public void setName(String name) {
        this.name.set(name);
    }

    public void setAge(int age) {
        this.age.set(age);
    }
}
