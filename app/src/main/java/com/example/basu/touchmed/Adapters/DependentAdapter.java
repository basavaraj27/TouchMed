package com.example.basu.touchmed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basu.touchmed.AddMedicineFormActivity;
import com.example.basu.touchmed.R;
import com.example.basu.touchmed.model.DepndentModel;

import java.util.List;

public class DependentAdapter extends RecyclerView.Adapter<DependentAdapter.DepndentViewHolder> {

    private Context context;
    private List<DepndentModel> depndentModellist;

    public DependentAdapter(Context context, List<DepndentModel> depndentModellist) {
        this.context = context;
        this.depndentModellist = depndentModellist;
    }

    public class DepndentViewHolder extends RecyclerView.ViewHolder
    {
        public TextView daytext;

        public DepndentViewHolder(View itemView) {
            super(itemView);
            daytext= (TextView) itemView.findViewById(R.id.daytext);

        }
    }

    @Override
    public DepndentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.customcrddependfrag,parent,false);
        return new DepndentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DepndentViewHolder holder, int position) {

        DepndentModel model=depndentModellist.get(position);
        holder.daytext.setText(model.getName());

        //set item click listener



    }

    @Override
    public int getItemCount() {
        return depndentModellist.size();
    }
}
