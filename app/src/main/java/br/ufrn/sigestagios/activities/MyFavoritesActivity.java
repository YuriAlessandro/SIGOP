package br.ufrn.sigestagios.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.adapters.OffersListAdapter;
import br.ufrn.sigestagios.models.Internship;
import br.ufrn.sigestagios.models.Offer;
import br.ufrn.sigestagios.utils.Constants;
import br.ufrn.sigestagios.utils.HttpHandler;

public class MyFavoritesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Offer> favoriteOffers = new ArrayList<>();
    private String TAG = "MyFavorites";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_favorite_offers_rv);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new OffersListAdapter(favoriteOffers, InternshipShowActivity.class, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        long currentUserId = getIntent().getLongExtra("currentUserId", 0);
        new GetFavoriteOffers().execute(String.valueOf(currentUserId));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Meus Favoritos");
    }

    private class GetFavoriteOffers extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            String url = Constants.URL_API_BASE + "/favorites?logged_user_id=" + strings[0];

            HttpHandler sh = new HttpHandler();

            String req_url = url;
            String jsonStr = sh.makeServiceCall(req_url, "GET");
            if (jsonStr != null) {
                try {
                    JSONObject resp = new JSONObject(jsonStr);
                    JSONArray offers_a = resp.getJSONArray("offers");

                    for(int i = 0; i < offers_a.length(); i++){
                        JSONObject offer = offers_a.getJSONObject(i);

                        String email = offer.getJSONArray("contacts").getJSONObject(0).getString("email");
                        String phone = offer.getJSONArray("contacts").getJSONObject(0).getString("phone");

                        String description = offer.getString("description");
                        String title = offer.getString("title");
                        String endOffer = offer.getString("endOffer");
                        String responsible = offer.getString("first_name") + " " + offer.getString("last_name");
                        String location = offer.getString("location");

                        int value = offer.getJSONArray("vacancies").getJSONObject(0).getInt("salary_total");
                        int tranpAux = offer.getJSONArray("vacancies").getJSONObject(0).getInt("salary_aids");
                        int numberPositions = offer.getJSONArray("vacancies").length();

                        int offerId = offer.getInt("idOffer");

                        Offer internship = new Internship(offerId, description, email, "Não definido",
                                responsible, numberPositions, value, tranpAux, endOffer,
                                title, phone, location);
                        favoriteOffers.add(internship);
                    }

                    Log.i(TAG, resp.toString());
                } catch (JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
