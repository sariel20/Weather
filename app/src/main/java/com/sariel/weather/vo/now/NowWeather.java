package com.sariel.weather.vo.now;

import com.sariel.weather.vo.forecast.Basic;
import com.sariel.weather.vo.forecast.DailyForecast;
import com.sariel.weather.vo.forecast.Update;

import java.util.List;

/**
 * Created by LiangCheng on 2018/1/29.
 */

public class NowWeather {
    private String status;
    private Basic basic;
    private Update update;
    private Now now;

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

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }
}
