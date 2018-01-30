package com.sariel.weather.vo.now;

/**
 * Created by ${LiangCheng} on 2018/1/29.
 */

public class Now {
    private String fl;//体感温度
    private String tmp;//温度
    private String cond_txt;//实况天气状况
    private String wind_dir;//风向
    private String wind_sc;//风力
    private String wind_spd;//风速，公里/小时
    private String hum;//相对湿度
    private String pcpn;//降水量
    private String pres;//大气压强
    private String vis;//能见度，默认单位：公里
    private String cloud;//云量

    public String getFl() {
        return fl;
    }

    public String getTmp() {
        return tmp;
    }

    public String getCond_txt() {
        return cond_txt;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public String getWind_sc() {
        return wind_sc;
    }

    public String getWind_spd() {
        return wind_spd;
    }

    public String getHum() {
        return hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public String getPres() {
        return pres;
    }

    public String getVis() {
        return vis;
    }

    public String getCloud() {
        return cloud;
    }
}
