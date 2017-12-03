package br.ufrn.sigestagios.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.Offer;

/**
 * Created by yurialessandro on 17/10/17.
 */

public class OffersListAdapter extends RecyclerView.Adapter<OffersListAdapter.ViewHolder> {
    private List<Offer> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionView;
        public TextView unitView;
        public TextView unitIDView;
        public TextView emailView;

        public ViewHolder(View itemView) {
            super(itemView);
            descriptionView = (TextView) itemView.findViewById(R.id.cDescription);
            unitView = (TextView) itemView.findViewById(R.id.cUnit);
            unitIDView = (TextView) itemView.findViewById(R.id.cUnitID);
            emailView = (TextView) itemView.findViewById(R.id.cEmail);
        }
    }

    public OffersListAdapter(List<Offer> data) {
        this.mDataSet = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.descriptionView.setText(mDataSet.get(position).getDescription());

        holder.unitView.setText(mDataSet.get(position).getUnit());
        holder.unitIDView.setText(String.valueOf(mDataSet.get(position).getIdUnit()));
        holder.emailView.setText(mDataSet.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
