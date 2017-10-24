package br.ufrn.sigestagios;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yurialessandro on 17/10/17.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Offer> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionView;
        public TextView authorView;
        public ViewHolder(View itemView) {
            super(itemView);
            descriptionView = (TextView) itemView.findViewById(R.id.cDescription);
            authorView = (TextView) itemView.findViewById(R.id.cResponsible);
        }
    }

    public MyAdapter(List<Offer> data) {
        mDataSet = data;
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
        holder.authorView.setText(mDataSet.get(position).getResponsible());
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
