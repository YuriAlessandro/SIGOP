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
import br.ufrn.sigestagios.models.User;
import br.ufrn.sigestagios.utils.Constants;
import br.ufrn.sigestagios.utils.HttpHandler;

import static br.ufrn.sigestagios.fragments.OffersFragment.SHOWER_CLASS;

public class MyOffersActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Offer> myOffers = new ArrayList<>();
    private String TAG = "MyOffers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_offers_rv);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new OffersListAdapter(myOffers, InternshipShowActivity.class);
        mRecyclerView.setAdapter(mAdapter);

        long currentUserId = getIntent().getLongExtra("currentUserId", 0);
        new getMyOffers().execute(String.valueOf(currentUserId));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ofertas criadas por mim");
    }

    private class getMyOffers extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            String url = Constants.URL_API_BASE + "/myOffers/" + strings[0];

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
                        String description = offer.getString("description");

                        Offer internship = new Internship(description, email);

                        myOffers.add(internship);
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
