package com.sariel.weather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sariel.weather.R;
import com.sariel.weather.base.BaseActivity;
import com.sariel.weather.vo.forecast.HeWeather;
import com.sariel.weather.vo.forecast.WeatherData;
import com.sariel.weather.vo.now.NowWeather;
import com.sariel.weather.vo.now.NowWeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LiangCheng on 2018/1/29.
 */

public class MainActivity extends BaseActivity {
    //    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    //    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //    @BindView(R.id.tv_now_location)
    TextView tv_now_location;
    //    @BindView(R.id.tv_now_cond)
    TextView tv_now_cond;
    //    @BindView(R.id.tv_now_wind)
    TextView tv_now_wind;

    TextView tv_uptime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        doBusiness();
    }

    public void initView() {
        setSupportActionBar(toolbar);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolbar);
        tv_now_location = findViewById(R.id.tv_now_location);
        tv_now_cond = findViewById(R.id.tv_now_cond);
        tv_now_wind = findViewById(R.id.tv_now_wind);
        tv_uptime = findViewById(R.id.tv_uptime);

        collapsingToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AreaActivity.class));
            }
        });
    }


    public void doBusiness() {
        Call<NowWeatherData> call = netWork().getNow(
                "CN101010100");
        call.enqueue(new Callback<NowWeatherData>() {
            @Override
            public void onResponse(Call<NowWeatherData> call, Response<NowWeatherData> response) {
                NowWeather nowWeather = response.body().getHeWeather6().get(0);
                collapsingToolbar.setTitle(nowWeather.getNow().getTmp() + "℃");
                tv_now_location.setText(nowWeather.getBasic().getCnty() + "  " + nowWeather.getBasic().getLocation());
                tv_now_cond.setText(nowWeather.getNow().getCond_txt());
                tv_now_wind.setText(nowWeather.getNow().getWind_dir() + nowWeather.getNow().getWind_sc());
                tv_uptime.setText("最后更新时间：" + nowWeather.getUpdate().getLoc());
            }

            @Override
            public void onFailure(Call<NowWeatherData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
    }
}
