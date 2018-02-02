package com.sariel.weather.vo.weather;

/**
 * Created by LiangCheng on 2018/1/29.
 */

public class Basic {
    private String location;//城市名称
    private String parent_city;//上级城市
    private String admin_area;//所属行政区
    private String cnty;//国家

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParent_city() {
        return parent_city;
    }

    public void setParent_city(String parent_city) {
        this.parent_city = parent_city;
    }

    public String getAdmin_area() {
        return admin_area;
    }

    public void setAdmin_area(String admin_area) {
        this.admin_area = admin_area;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }
}
