package com.developer.bismillah.lapakikan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.bismillah.lapakikan.R;
import com.developer.bismillah.lapakikan.activity.LapakActivity;
import com.developer.bismillah.lapakikan.modal.IkanLapak;

import java.util.List;

/**
 * Created by Afdolash on 5/28/2017.
 */

public class IkanLapakAdapter extends RecyclerView.Adapter<IkanLapakAdapter.MyViewHolder> {
    private Context mContext;
    private List<IkanLapak> ikanLapakList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    public IkanLapakAdapter(Context mContext, List<IkanLapak> ikanLapakList) {
        this.mContext = mContext;
        this.ikanLapakList = ikanLapakList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lapak, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LapakActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ikanLapakList.size();
    }


}
