package com.sariel.weather.vo.lifestyle;

/**
 * Created by ${LiangCheng} on 2018/1/29.
 */

public class LifeStyleInfo {
    private String brf;//生活指数简介
    private String txt;//生活指数详细描述
    private String type;//生活指数类型

    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
