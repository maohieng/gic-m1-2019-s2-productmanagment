package edu.itc.gic.m1.s2.databinding_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.itc.gic.m1.s2.databinding_sample.databinding.ActivityMainDatabindingBinding;

public class MainDatabindingActivity extends AppCompatActivity {

//    TextView textVoteNumber;

    ActivityMainDatabindingBinding mBinding;
    MyDatabindingViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_databinding);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_databinding);

        // Set lifecycleOwner that should be used for observing changes
        // of LiveData in this binding
        mBinding.setLifecycleOwner(this);

        mViewModel = new ViewModelProvider(this).get(MyDatabindingViewModel.class);

        mBinding.setViewModel(mViewModel);

//        TextView textName = findViewById(R.id.textView);
//        textName.setText(name);
//
//        TextView textEmail = findViewById(R.id.textView2);
//        textEmail.setText(email);
//
//        textVoteNumber = findViewById(R.id.textView3);
//        textVoteNumber.setText("0");
//
//        Button buttonVoteMe = findViewById(R.id.button);
//        buttonVoteMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String number = textVoteNumber.getText().toString();
//                if (!number.isEmpty()) {
//                    int numbVote = Integer.parseInt(number);
//                    textVoteNumber.setText(++numbVote + "");
//                }
//            }
//        });
    }
}
