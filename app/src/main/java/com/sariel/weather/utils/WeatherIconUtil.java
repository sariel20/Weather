package com.sariel.weather.utils;

import android.support.design.widget.FloatingActionButton;

import com.sariel.weather.R;

/**
 * Created by LiangCheng on 2018/1/31.
 */

public class WeatherIconUtil {

    public void loadWeatherIcon(int code, FloatingActionButton imageView) {
        switch (code) {
            case 100:
                imageView.setImageResource(R.mipmap.w100);
                break;
            case 101:
                imageView.setImageResource(R.mipmap.w101);
                break;
            case 103:
                imageView.setImageResource(R.mipmap.w103);
                break;
            case 104:
                imageView.setImageResource(R.mipmap.w104);
                break;
            case 300:
                imageView.setImageResource(R.mipmap.w300);
                break;
            case 302:
                imageView.setImageResource(R.mipmap.w302);
                break;
            case 305:
                imageView.setImageResource(R.mipmap.w305);
                break;
            case 306:
                imageView.setImageResource(R.mipmap.w306);
                break;
            case 307:
                imageView.setImageResource(R.mipmap.w307);
                break;
            case 400:
                imageView.setImageResource(R.mipmap.w400);
                break;
            case 401:
                imageView.setImageResource(R.mipmap.w401);
                break;
            case 402:
                imageView.setImageResource(R.mipmap.w402);
                break;
            case 404:
                imageView.setImageResource(R.mipmap.w404);
                break;
            case 407:
                imageView.setImageResource(R.mipmap.w407);
                break;
            case 500:
                imageView.setImageResource(R.mipmap.w500);
                break;
            case 501:
                imageView.setImageResource(R.mipmap.w501);
                break;
            case 502:
                imageView.setImageResource(R.mipmap.w502);
                break;
            case 503:
                imageView.setImageResource(R.mipmap.w503);
                break;
            case 504:
                imageView.setImageResource(R.mipmap.w504);
                break;
            default:
                imageView.setImageResource(R.mipmap.w999);
                break;
        }
    }
}
