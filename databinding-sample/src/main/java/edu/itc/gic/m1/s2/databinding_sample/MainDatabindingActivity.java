package edu.itc.gic.m1.s2.databinding_sample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import edu.itc.gic.m1.s2.databinding_sample.databinding.ActivityMainDatabindingBinding;

public class MainDatabindingActivity extends AppCompatActivity {

    private static final String TAG = "MainDatabindingActivity";

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

        // These 2 invokes are equivalent
//        mBinding.setViewModel(mViewModel);
        mBinding.setVariable(BR.viewModel, mViewModel);

        // String format in Java
        String myPhone = "This is my phone number: %s";
        String message = String.format(myPhone, "097123456");
        Log.i(TAG, "onCreate: " + message);

        // String format from string resource to Java
        String msgDetail = String.format(getString(R.string.confirm_profile_detail_fmt),
                mViewModel.getUser().getName(),
                mViewModel.getUser().getEmail());

        // See binding string format in XML layout

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
