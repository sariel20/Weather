package com.sariel.weather.vo.location;

import org.litepal.crud.DataSupport;

/**
 * Created by ${LiangCheng} on 2018/1/29.
 */

public class City extends DataSupport {

    private int id;

    private String name;

    private int provinceId;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
