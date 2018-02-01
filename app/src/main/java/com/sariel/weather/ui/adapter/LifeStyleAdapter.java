package com.sariel.weather.ui.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sariel.weather.R;
import com.sariel.weather.vo.lifestyle.LifeStyleInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiangCheng on 2018/1/30.
 */

public class LifeStyleAdapter extends RecyclerView.Adapter<LifeStyleAdapter.ViewHoder> {

    private Context context;
    private List<LifeStyleInfo> lifeStyleInfos = new ArrayList<>();

    public LifeStyleAdapter(Context context, List<LifeStyleInfo> list) {
        this.context = context;
        this.lifeStyleInfos = list;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_lifestyle, parent, false);
        final ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, final int position) {
        holder.tv_lifestyle_brf.setText(lifeStyleInfos.get(position).getBrf());
        if ("comf".equals(lifeStyleInfos.get(position).getType())) {
            holder.tv_lifestyle_type.setText("舒适度");
        } else if ("cw".equals(lifeStyleInfos.get(position).getType())) {
            holder.tv_lifestyle_type.setText("洗车指数");
        } else if ("drsg".equals(lifeStyleInfos.get(position).getType())) {
            holder.tv_lifestyle_type.setText("穿衣指数");
        } else if ("flu".equals(lifeStyleInfos.get(position).getType())) {
            holder.tv_lifestyle_type.setText("感冒指数");
        } else if ("sport".equals(lifeStyleInfos.get(position).getType())) {
            holder.tv_lifestyle_type.setText("运动指数");
        } else if ("trav".equals(lifeStyleInfos.get(position).getType())) {
            holder.tv_lifestyle_type.setText("旅游指数");
        } else if ("uv".equals(lifeStyleInfos.get(position).getType())) {
            holder.tv_lifestyle_type.setText("紫外线指数");
        } else if ("air".equals(lifeStyleInfos.get(position).getType())) {
            holder.tv_lifestyle_type.setText("空气污染扩散");
        }

        holder.rl_lifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, lifeStyleInfos.get(position).getTxt(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lifeStyleInfos.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lifestyle_brf)
        TextView tv_lifestyle_brf;
        @BindView(R.id.tv_lifestyle_type)
        TextView tv_lifestyle_type;
        @BindView(R.id.rl_lifestyle)
        RelativeLayout rl_lifestyle;

        public ViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
