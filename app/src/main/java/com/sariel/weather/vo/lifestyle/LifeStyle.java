package com.sariel.weather.vo.lifestyle;

import com.sariel.weather.vo.forecast.Basic;
import com.sariel.weather.vo.forecast.Update;

import java.util.List;

/**
 * Created by LiangCheng on 2018/1/29.
 */

public class LifeStyle {
    private String status;
    private Basic basic;
    private Update update;
    private List<LifeStyleInfo> lifestyle;

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

    public List<LifeStyleInfo> getLifeStyleInfos() {
        return lifestyle;
    }

    public void setLifeStyleInfos(List<LifeStyleInfo> lifeStyleInfos) {
        this.lifestyle = lifeStyleInfos;
    }
}
