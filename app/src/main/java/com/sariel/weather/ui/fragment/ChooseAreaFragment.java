package com.sariel.weather.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sariel.weather.R;
import com.sariel.weather.net.ApiServiceible;
import com.sariel.weather.net.Utility;
import com.sariel.weather.vo.location.City;
import com.sariel.weather.vo.location.County;
import com.sariel.weather.vo.location.Province;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ${LiangCheng} on 2018/1/29.
 */

public class ChooseAreaFragment extends Fragment {

    TextView tv_choose_area;
    ListView lv_choose_area;
    ArrayAdapter<String> adapter;
    List<String> dataList = new ArrayList<>();

    /*省列表*/
    private List<Province> provinces = new ArrayList<>();
    /*市列表*/
    private List<City> cities = new ArrayList<>();
    /*县列表*/
    private List<County> counties = new ArrayList<>();

    /*选中数据*/
    private Province selectProvince;
    private City selectCity;
    private County selectCounty;

    /*级别*/
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    /*选中级别*/
    private int currentLevel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_area, container, false);
        tv_choose_area = view.findViewById(R.id.tv_choose_area);
        lv_choose_area = view.findViewById(R.id.lv_choose_area);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dataList);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_choose_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectProvince = provinces.get(position);
                    /*查詢市级*/
                } else if (currentLevel == LEVEL_CITY) {
                    selectCity = cities.get(position);
                    /*查询县级*/
                } else {
                    selectCounty = counties.get(position);
                    /*返回天气界面*/
                }
            }
        });
        queryProvinces();
    }

    /*查询省级数据*/
    private void queryProvinces() {
        tv_choose_area.setText("中国");
        provinces = DataSupport.findAll(Province.class);
        if (provinces.size() > 0) {
            dataList.clear();
            for (Province province : provinces) {
                dataList.add(province.getName());
            }
            adapter.notifyDataSetChanged();
            lv_choose_area.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryProvince();
        }
    }

    /*查詢市级数据*/
    private void queryCity() {
        tv_choose_area.setText(selectProvince.getName());
        cities = DataSupport.where("provinceId = ?", String.valueOf(selectProvince.getId())).find(City.class);
        if (cities.size() > 0) {
            dataList.clear();
            for (City city : cities) {
                dataList.add(city.getName());
            }
            adapter.notifyDataSetChanged();
            lv_choose_area.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            /*从服务器查询市级数据*/
        }
    }

    private void queryProvince() {
        Call<ResponseBody> call = queryFromServer().getProvinces();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    if (!json.isEmpty()) {
                        boolean result = Utility.handleProvinceResponse(json);
                        if (result) {
                            queryProvinces();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("------------>", "failure");
            }
        });
    }

    private ApiServiceible queryFromServer() {
        ApiServiceible apiServiceible = null;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://guolin.tech/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServiceible = retrofit.create(ApiServiceible.class);
        return apiServiceible;
    }

}