package com.demo.subbucocociti_minitask.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.subbucocociti_minitask.R;
import com.demo.subbucocociti_minitask.model.ShowCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subbu on 23/6/17.
 */

public class Adapter_ListData extends RecyclerView.Adapter<Adapter_ListData.Viewholder> {
    private static final String TAG = Adapter_ListData.class.getSimpleName();

    private AppCompatActivity mActivity;
    private List<ShowCase> listShowCase = new ArrayList<>();

    public Adapter_ListData(AppCompatActivity mActivity, List<ShowCase> listShowCase)
    {
        this.mActivity = mActivity;
        this.listShowCase = listShowCase;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView tv_id,tv_title,tv_description,tv_year,tv_user_id;
        public Viewholder(View itemView) {
            super(itemView);

            tv_id = (TextView) itemView.findViewById(R.id.tv_item_id);
            tv_user_id = (TextView) itemView.findViewById(R.id.tv_item_user_id);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_item_description);
            tv_year = (TextView) itemView.findViewById(R.id.tv_item_year);
        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        ShowCase showCase  = listShowCase.get(position);

        holder.tv_id.setText(showCase.getId().toString());
        holder.tv_user_id.setText(showCase.getUserId().toString());
        holder.tv_title.setText(showCase.getTitle());
        holder.tv_description.setText(showCase.getDescription());
        holder.tv_year.setText(showCase.getYear());
    }

    @Override
    public int getItemCount() {
        return listShowCase.size();
    }
}
