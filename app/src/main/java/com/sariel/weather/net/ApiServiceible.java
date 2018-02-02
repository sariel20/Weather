package com.sariel.weather.net;

import com.sariel.weather.vo.weather.WeatherData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by LiangCheng on 2018/1/29.
 */

public interface ApiServiceible {

    /**
     * 获取天气数据集合
     *
     * @param location
     * @return
     */
    @GET("weather?key=a5b3ea1045b245e6bd55b18a951b72a2")
    Call<WeatherData> getWeatherData(@Query("location") String location);


    /**
     * 获取省级数据
     *
     * @return
     */
    @GET("china/")
    Call<ResponseBody> getProvinces();

    /**
     * 获取市级数据
     *
     * @return
     */
    @GET("china/{provinceId}")
    Call<ResponseBody> getCitis(
            @Path("provinceId") int provinceId);

    /**
     * 获取县级数据
     *
     * @param provinceId
     * @return
     */
    @GET("china/{provinceId}/{cityId}")
    Call<ResponseBody> getCountyId(
            @Path("provinceId") int provinceId, @Path("cityId") int cityId);

    /**
     * 获取bing每日一图
     *
     * @return
     */
    @GET("bing_pic")
    Call<ResponseBody> getPic();

}
