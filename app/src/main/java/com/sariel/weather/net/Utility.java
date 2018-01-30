package com.sariel.weather.net;

import com.sariel.weather.vo.location.City;
import com.sariel.weather.vo.location.County;
import com.sariel.weather.vo.location.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ${LiangCheng} on 2018/1/29.
 */

public class Utility {
    /**
     * 解析省级数据
     *
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response) {
        try {
            JSONArray allProvinces = new JSONArray(response);
            for (int i = 0; i < allProvinces.length(); i++) {
                JSONObject provinceObject = allProvinces.getJSONObject(i);
                Province province = new Province();
                province.setProvinceCode(provinceObject.getInt("id"));
                province.setProvinceName(provinceObject.getString("name"));
                province.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解析市级数据
     *
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        try {
            JSONArray allCities = new JSONArray(response);
            for (int i = 0; i < allCities.length(); i++) {
                JSONObject cityObject = allCities.getJSONObject(i);
                City city = new City();
                city.setCityCode(cityObject.getInt("id"));
                city.setCityName(cityObject.getString("name"));
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解析县级数据
     *
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        try {
            JSONArray allCountys = new JSONArray(response);
            for (int i = 0; i < allCountys.length(); i++) {
                JSONObject countyObject = allCountys.getJSONObject(i);
                County county = new County();
                county.setCountyCode(countyObject.getInt("id"));
                county.setCountyName(countyObject.getString("name"));
                county.setWeather_id(countyObject.getString("weather_id"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
