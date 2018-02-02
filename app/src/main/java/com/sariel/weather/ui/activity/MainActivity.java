package com.sariel.weather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sariel.weather.R;
import com.sariel.weather.base.BaseActivity;
import com.sariel.weather.net.ApiServiceible;
import com.sariel.weather.ui.adapter.ForecastAdapter;
import com.sariel.weather.ui.adapter.LifeStyleAdapter;
import com.sariel.weather.utils.GlideUtils;
import com.sariel.weather.utils.WeatherIconUtil;
import com.sariel.weather.vo.weather.DailyForecast;
import com.sariel.weather.vo.weather.HeWeather;
import com.sariel.weather.vo.weather.LifeStyleInfo;
import com.sariel.weather.vo.weather.WeatherData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by LiangCheng on 2018/1/29.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_now_tmp)
    TextView tv_now_tmp;
    @BindView(R.id.tv_now_cond)
    TextView tv_now_cond;
    @BindView(R.id.tv_now_wind)
    TextView tv_now_wind;
    @BindView(R.id.tv_uptime)
    TextView tv_uptime;
    @BindView(R.id.tv_now_fl)
    TextView tv_now_fl;
    @BindView(R.id.iv_weather_icon)
    FloatingActionButton iv_weather_icon;
    @BindView(R.id.iv_background)
    ImageView iv_background;

    /*默认查询北京天气*/
    private String areaCode = "CN101010300";

    private String code = "";

    /*获取并展示未来三天天气*/
    @BindView(R.id.rv_forecast)
    RecyclerView rv_forecast;

    private ForecastAdapter adapter;
    private List<DailyForecast> dailyForecasts = new ArrayList<>();

    /*获取并展示当天生活指数*/
    @BindView(R.id.rv_lifestyle)
    RecyclerView rv_lifestyle;
    private LifeStyleAdapter lifeStyleAdapter;
    private List<LifeStyleInfo> lifeStyleInfos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        initLifeStyleData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        doBusiness();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        code = intent.getStringExtra("code");
        if (code != null) {
            areaCode = code;
        }
        doBusiness();
    }

    public void initView() {
        setSupportActionBar(toolbar);

        steepStatusBar();

        loadPic();

        iv_weather_icon.setOnClickListener(new View.OnClickListener() {
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

    private void initLifeStyleData() {
        rv_lifestyle.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        lifeStyleAdapter = new LifeStyleAdapter(getApplicationContext(), lifeStyleInfos);
        rv_lifestyle.setAdapter(lifeStyleAdapter);
    }

    public void doBusiness() {
        showLoading();
        Call<WeatherData> call = netWork().getWeatherData(areaCode);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                try {
                    HeWeather weather = response.body().getHeWeather6().get(0);
                /*实时天气*/
                    collapsingToolbar.setTitle(weather.getBasic().getAdmin_area() + "  " + weather.getBasic().getLocation());
                    tv_now_tmp.setText(weather.getNow().getTmp());
                    tv_now_cond.setText(weather.getNow().getCond_txt());
                    tv_now_wind.setText(weather.getNow().getWind_dir() + weather.getNow().getWind_sc());
                    tv_uptime.setText("最后更新时间：" + weather.getUpdate().getLoc());
                    tv_now_fl.setText("体感温度：" + weather.getNow().getFl() + " ℃");

                    new WeatherIconUtil().loadWeatherIcon(weather.getNow().getCond_code(), iv_weather_icon);

                /*未来三天天气*/
                    dailyForecasts.clear();
                    dailyForecasts.addAll(weather.getDaily_forecast());
                    adapter.notifyDataSetChanged();

                /*生活指数*/
                    lifeStyleInfos.clear();
                    lifeStyleInfos.addAll(weather.getLifeStyleInfos());
                    lifeStyleAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                dismissLoading();
            }

            @Override
            public void onFailure(retrofit2.Call<WeatherData> call, Throwable t) {
                t.printStackTrace();
                dismissLoading();
            }
        });
    }


    public void loadPic() {
        Call<ResponseBody> call = netWorkGetPic().getPic();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    final String bingPic = response.body().string();
                    new GlideUtils().loadImage(getApplicationContext(), bingPic, iv_background);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public ApiServiceible netWorkGetPic() {
        ApiServiceible apiServiceible = null;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://guolin.tech/api/")
                .build();
        apiServiceible = retrofit.create(ApiServiceible.class);
        return apiServiceible;
    }

}
