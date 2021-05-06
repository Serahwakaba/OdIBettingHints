package com.example.odibettinghints.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odibettinghints.model.Anime;
import  com.example.odibettinghints.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Anime> mData;

    public RecyclerViewAdapter(Context mContext, List<Anime> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

     View view ;
     LayoutInflater inflater = LayoutInflater.from(mContext);
     view = inflater.inflate (R.layout.anime_row_item, parent, false) ;


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.tv_time.setText(mData.get(position).getMatch_start_time());
        holder.tv_matchOne.setText(mData.get(position).getHome_team());
        holder.tv_matchTwo.setText(mData.get(position).getAway_team());
        holder.tv_scoreOne.setText((int) mData.get(position).getHome_strength());
        holder.tv_scoreTwo.setText((int) mData.get(position).getAway_strength());
        holder.tv_tips.setText(mData.get(position).getPredictions());
        holder.tv_odds.setText((int) mData.get(position).getOdds());






    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class MyViewHolder   extends RecyclerView.ViewHolder{


        public TextView tv_time;
        public TextView tv_border;
        public TextView tv_matchOne;
        public TextView tv_matchTwo;
        public TextView tv_scoreOne;
        public TextView tv_scoreTwo;
        public TextView tv_border1;
        public TextView tv_tips;
        public TextView tv_border2;
        public TextView tv_odds;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_time = (TextView) itemView.findViewById(R.id.time);
            this.tv_border = (TextView) itemView.findViewById(R.id.border);
            this.tv_matchOne= (TextView) itemView.findViewById(R.id.matchOne);
            this.tv_matchTwo = (TextView) itemView.findViewById(R.id.matchTwo);
            this.tv_scoreOne = (TextView) itemView.findViewById(R.id.scoreOne);
            this.tv_scoreTwo = (TextView) itemView.findViewById(R.id.scoreTwo);
            this.tv_border1 = (TextView) itemView.findViewById(R.id.border1);
            this.tv_tips = (TextView) itemView.findViewById(R.id.tips);
            this.tv_border2 = (TextView) itemView.findViewById(R.id.border2);
            this.tv_odds = (TextView) itemView.findViewById(R.id.odds);
        }
    }
}
