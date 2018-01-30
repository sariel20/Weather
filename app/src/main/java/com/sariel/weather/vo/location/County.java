package com.sariel.weather.vo.location;

import org.litepal.crud.DataSupport;

/**
 * Created by ${LiangCheng} on 2018/1/29.
 */

public class County extends DataSupport {

    private int id;

    private int countyCode;

    private String countyName;

    private String weather_id;

    private int cityId;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }

    public String getWeather_id() {
        return weather_id;
    }

    public int getId() {
        return id;
    }

    public int getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(int countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
