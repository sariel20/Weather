package com.sariel.weather.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sariel.weather.R;
import com.sariel.weather.ui.fragment.ChooseAreaFragment;

/**
 * Created by ${LiangCheng} on 2018/1/29.
 */

public class AreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        replace(new ChooseAreaFragment());
    }

    private void replace(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commitAllowingStateLoss();
    }
}
