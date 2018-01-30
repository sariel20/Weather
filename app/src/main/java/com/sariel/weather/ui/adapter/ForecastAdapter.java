package com.sariel.weather.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sariel.weather.R;
import com.sariel.weather.vo.forecast.DailyForecast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiangCheng on 2018/1/30.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHoder> {

    private Context context;
    private List<DailyForecast> dailyForecasts = new ArrayList<>();

    public ForecastAdapter(Context context, List<DailyForecast> list) {
        this.context = context;
        this.dailyForecasts = list;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_forecast, parent, false);
        final ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
        holder.tv_date.setText(dailyForecasts.get(position).getDate());
        holder.tv_cond.setText("白天:" + dailyForecasts.get(position).getCond_txt_d() + "/" +
                "夜间:" + dailyForecasts.get(position).getCond_txt_n());
        holder.tv_tmp.setText(dailyForecasts.get(position).getTmp_max() + "/" +
                dailyForecasts.get(position).getTmp_min());
    }

    @Override
    public int getItemCount() {
        return dailyForecasts.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView tv_date;
        TextView tv_cond;
        TextView tv_tmp;

        public ViewHoder(View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_cond = itemView.findViewById(R.id.tv_cond);
            tv_tmp = itemView.findViewById(R.id.tv_tmp);
        }
    }
}
