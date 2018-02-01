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
import com.sariel.weather.vo.forecast.DailyForecast;
import com.sariel.weather.vo.forecast.HeWeather;
import com.sariel.weather.vo.forecast.WeatherData;
import com.sariel.weather.vo.lifestyle.LifeStyleData;
import com.sariel.weather.vo.lifestyle.LifeStyleInfo;
import com.sariel.weather.vo.now.NowWeather;
import com.sariel.weather.vo.now.NowWeatherData;

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
        code = getIntent().getStringExtra("code");
        if (code != null) {
            areaCode = code;
        }
        doBusiness();
        getForecast();
        getLifeStyle();
    }

    public void initView() {
        setSupportActionBar(toolbar);

        steepStatusBar();

//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//        String picUrl = pref.getString("bing_pic", null);
//        if (picUrl != null) {
//            new GlideUtils().loadImage(getApplicationContext(), picUrl, iv_background);
//        } else {
        loadPic();
//        }

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
        Call<NowWeatherData> call = netWork().getNow(areaCode);
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

                new WeatherIconUtil().loadWeatherIcon(nowWeather.getNow().getCond_code(), iv_weather_icon);
            }

            @Override
            public void onFailure(retrofit2.Call<NowWeatherData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getForecast() {

        Call<WeatherData> call = netWork().getWeatherData(areaCode);
        showLoading();
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                dailyForecasts.clear();
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

    private void getLifeStyle() {
        Call<LifeStyleData> call = netWork().getLifeStyle(areaCode);
        call.enqueue(new Callback<LifeStyleData>() {
            @Override
            public void onResponse(Call<LifeStyleData> call, Response<LifeStyleData> response) {
                lifeStyleInfos.clear();
                lifeStyleInfos.addAll(response.body().getHeWeather6().get(0).getLifeStyleInfos());
                lifeStyleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LifeStyleData> call, Throwable t) {
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
//                    SharedPreferences.Editor editor = PreferenceManager
//                            .getDefaultSharedPreferences(MainActivity.this).edit();
//                    editor.putString("bing_pic", bingPic);
//                    editor.apply();
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

    @Override
    public void onClick(View v) {
    }
}
