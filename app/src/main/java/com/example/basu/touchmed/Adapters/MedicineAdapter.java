package com.example.basu.touchmed.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basu.touchmed.R;
import com.example.basu.touchmed.model.Medicine;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private Context c;
    private List<Medicine> medicinelist;

    public MedicineAdapter(Context c, List<Medicine> medicinelist) {
        this.c = c;
        this.medicinelist = medicinelist;
    }

    public class MedicineViewHolder extends RecyclerView.ViewHolder
    {
        public TextView medname;

        public MedicineViewHolder(View itemView) {
            super(itemView);
            medname= (TextView) itemView.findViewById(R.id.medname);
        }
    }

    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_card_medlayout, parent, false);
        return new MedicineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int position) {
        Medicine m = medicinelist.get(position);
        holder.medname.setText(m.getMedname());
    }

    @Override
    public int getItemCount() {
        return medicinelist.size();
    }
}
