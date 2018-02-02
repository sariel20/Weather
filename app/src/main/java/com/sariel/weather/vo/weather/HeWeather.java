package com.sariel.weather.vo.weather;

import java.util.List;

/**
 * Created by LiangCheng on 2018/1/29.
 */

public class HeWeather {
    private String status;
    private Basic basic;
    private Update update;
    private List<DailyForecast> daily_forecast;
    private List<LifeStyleInfo> lifestyle;
    private Now now;

    public List<LifeStyleInfo> getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(List<LifeStyleInfo> lifestyle) {
        this.lifestyle = lifestyle;
    }

    public List<LifeStyleInfo> getLifeStyleInfos() {
        return lifestyle;
    }

    public void setLifeStyleInfos(List<LifeStyleInfo> lifeStyleInfos) {
        this.lifestyle = lifeStyleInfos;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public List<DailyForecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }
}
