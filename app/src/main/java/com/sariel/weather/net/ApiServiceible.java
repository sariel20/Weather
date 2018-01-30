package com.sariel.weather.net;

import com.sariel.weather.vo.forecast.WeatherData;
import com.sariel.weather.vo.now.NowWeatherData;

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
     * 3天天气预报
     *
     * @param location 城市名称或代码
     * @return
     */
    @GET("forecast?key=a5b3ea1045b245e6bd55b18a951b72a2")
    Call<WeatherData> getWeatherData(
            @Query("location") String location);

    /**
     * 实况天气
     *
     * @param location 城市名称或代码
     * @return
     */
    @GET("now?key=a5b3ea1045b245e6bd55b18a951b72a2")
    Call<NowWeatherData> getNow(
            @Query("location") String location);

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
}
