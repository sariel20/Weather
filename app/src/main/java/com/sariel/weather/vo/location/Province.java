package com.sariel.weather.vo.location;

import org.litepal.crud.DataSupport;

/**
 * Created by ${LiangCheng} on 2018/1/29.
 */

public class Province extends DataSupport {

    private int id;

    private int provinceCode;

    private String provinceName;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
