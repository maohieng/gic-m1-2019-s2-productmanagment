package edu.itc.m1.databinding.recycler;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Arrays;

import edu.itc.m1.databinding.recycler.databinding.ActivityMainBinding;
import edu.itc.m1.databinding.recycler.widget.BindableRecyclerAdapter;
import edu.itc.m1.databinding.recycler.widget.RecyclerConfiguration;

public class MainActivity extends AppCompatActivity {

    private static final Integer[] sSimulatedAmount = {
            1, 2, 5, 10, 15, 20, 50
    };

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        final BindableRecyclerAdapter<Integer> adapter = new BindableRecyclerAdapter<>(this,
                R.layout.item_list_top_up_amount, BR.amount);

        RecyclerConfiguration config = new RecyclerConfiguration.Builder()
                .setHasFixedSize(true)
                .setAdapter(adapter)
                .setLayoutManager(new GridLayoutManager(this, 2))
                .setItemDecoration(new AmountGridItemDecor(this, 2))
                .build();

        mBinding.setConfig(config);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyNewDataSetChanged(Arrays.asList(sSimulatedAmount));
            }
        }, 2000);
    }
}
