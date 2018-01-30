package com.sariel.weather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sariel.weather.R;
import com.sariel.weather.base.BaseActivity;
import com.sariel.weather.ui.adapter.ForecastAdapter;
import com.sariel.weather.vo.forecast.DailyForecast;
import com.sariel.weather.vo.forecast.HeWeather;
import com.sariel.weather.vo.forecast.WeatherData;
import com.sariel.weather.vo.now.NowWeather;
import com.sariel.weather.vo.now.NowWeatherData;

import java.util.ArrayList;
import java.util.List;

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
    //    @BindView(R.id.tv_now_tmp)
    TextView tv_now_tmp;
    //    @BindView(R.id.tv_now_cond)
    TextView tv_now_cond;
    //    @BindView(R.id.tv_now_wind)
    TextView tv_now_wind;

    TextView tv_uptime;

    TextView tv_now_fl;

    /*默认查询北京天气*/
    private String areaCode = "CN101010300";

    private String code = "";

    /*获取并展示未来三天天气*/
    private RecyclerView rv_forecast;
    private ForecastAdapter adapter;
    private List<DailyForecast> dailyForecasts = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        code = getIntent().getStringExtra("code");
        if (code != null) {
            areaCode = code;
        }
        doBusiness();
        getForecast();
    }

    public void initView() {
        setSupportActionBar(toolbar);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolbar);
        tv_now_tmp = findViewById(R.id.tv_now_tmp);
        tv_now_cond = findViewById(R.id.tv_now_cond);
        tv_now_wind = findViewById(R.id.tv_now_wind);
        tv_uptime = findViewById(R.id.tv_uptime);
        tv_now_fl = findViewById(R.id.tv_now_fl);
        rv_forecast = findViewById(R.id.rv_forecast);

        collapsingToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AreaActivity.class));
            }
        });
    }

    private void initData() {
        rv_forecast.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ForecastAdapter(getApplicationContext(), dailyForecasts);
        rv_forecast.setAdapter(adapter);
    }

    public void doBusiness() {
        Call<NowWeatherData> call = netWork().getNow(
                areaCode);
        call.enqueue(new Callback<NowWeatherData>() {
            @Override
            public void onResponse(Call<NowWeatherData> call, Response<NowWeatherData> response) {
                NowWeather nowWeather = response.body().getHeWeather6().get(0);
                collapsingToolbar.setTitle(nowWeather.getBasic().getAdmin_area() + "  " + nowWeather.getBasic().getLocation());
                tv_now_tmp.setText(nowWeather.getNow().getTmp());
                tv_now_cond.setText(nowWeather.getNow().getCond_txt());
                tv_now_wind.setText(nowWeather.getNow().getWind_dir() + nowWeather.getNow().getWind_sc());
                tv_uptime.setText("最后更新时间：" + nowWeather.getUpdate().getLoc());
                tv_now_fl.setText("体感温度：" + nowWeather.getNow().getFl() + " ℃");
            }

            @Override
            public void onFailure(Call<NowWeatherData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getForecast() {
        showLoading();
        Call<WeatherData> call = netWork().getWeatherData(areaCode);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                HeWeather heWeather = response.body().getHeWeather6().get(0);
                dailyForecasts.addAll(heWeather.getDaily_forecast());
                adapter.notifyDataSetChanged();
                dismissLoading();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

                dismissLoading();
            }
        });
    }


    @Override
    public void onClick(View v) {
    }
}
