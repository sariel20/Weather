package com.sariel.weather.eventbus;

/**
 * Created by LiangCheng on 2018/1/30.
 */

public class UpdateLocal {
    String areaCode;

    public UpdateLocal(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }
}
