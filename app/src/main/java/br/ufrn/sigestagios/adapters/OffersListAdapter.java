package br.ufrn.sigestagios.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.activities.RegistrationFormActivity;
import br.ufrn.sigestagios.models.Offer;
import br.ufrn.sigestagios.models.User;
import br.ufrn.sigestagios.utils.Constants;
import br.ufrn.sigestagios.utils.HttpHandler;

/**
 * Created by yurialessandro on 17/10/17.
 */

public class OffersListAdapter extends RecyclerView.Adapter<OffersListAdapter.ViewHolder> {
    private List<Offer> mDataSet;
    private Class shower_class;
    private Context ctx;

    private int lastPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionView;
        public TextView unitView;
        public TextView unitIDView;
        public TextView emailView;
        public ImageView favoriteBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            descriptionView = (TextView) itemView.findViewById(R.id.cDescription);
            unitView = (TextView) itemView.findViewById(R.id.cUnit);
            emailView = (TextView) itemView.findViewById(R.id.cEmail);
            favoriteBtn = itemView.findViewById(R.id.favoritesOffer);
        }
    }

    public OffersListAdapter(List<Offer> data, Class shower_class, Context ctx) {
        this.mDataSet = data;
        this.shower_class = shower_class;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.descriptionView.setText(mDataSet.get(position).getDescription());
        holder.unitView.setText(mDataSet.get(position).getUnit());
        holder.emailView.setText(mDataSet.get(position).getEmail());

        boolean fromSigaa = mDataSet.get(position).isFromSigaa();
        if (fromSigaa){
            holder.favoriteBtn.setVisibility(View.GONE);
        }else{
            holder.favoriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ctx, "Carregando...", Toast.LENGTH_SHORT).show();
                    new SetOfferAsFav().execute(String.valueOf(User.getActualUserId()),
                                                String.valueOf(mDataSet.get(position).getOfferId()));
                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), shower_class);
                intent.putExtra("OFFER", mDataSet.get(position));
                view.getContext().startActivity(intent);
            }
        });

        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private class SetOfferAsFav extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            String url = Constants.URL_API_BASE + "/favorites?logged_user_id=" + strings[0] + "&offer_id=" + strings[1];

            HttpHandler sh = new HttpHandler();

            String req_url = url;
            String jsonStr = sh.makeServiceCall(req_url, "POST");
            if (jsonStr != null) {
                try {
                    JSONObject resp = new JSONObject(jsonStr);
                    Log.e("A", resp.toString());
                    if (resp.getBoolean("success")){
                        return true;
                    }else{
                        return false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean){
                Toast.makeText(ctx, "Adicionado aos favoritos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ctx, "Erro inesperado", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
