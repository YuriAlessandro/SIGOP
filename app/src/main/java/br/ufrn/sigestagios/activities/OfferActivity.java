package br.ufrn.sigestagios.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.sigestagios.adapters.OfferFragmentPagerAdapter;
import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.Offer;
import br.ufrn.sigestagios.utils.Constants;
import br.ufrn.sigestagios.utils.HttpHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OfferActivity extends AppCompatActivity {

//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    OfferFragmentPagerAdapter pagerAdapter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;

    List<List<Offer>> offers = new ArrayList<List<Offer>>();

    private String TAG = OfferActivity.class.getSimpleName();

    private static final int REGISTER = 0;

    private String apiKey = "iZOuoL4kPb1xuJUeLD3AGwU3xKCcJ5uwrctBfwX6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        for (int i = 0; i < 3; i++) {
            offers.add(new ArrayList<Offer>());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new OfferFragmentPagerAdapter(getSupportFragmentManager(),
                OfferActivity.this, offers);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //New toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Sidebar Menu
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = this.getSharedPreferences("user_info", 0);
        String accessToken = preferences.getString(Constants.KEY_ACCESS_TOKEN, null);

        if(accessToken != null){
            new GetReq().execute("usuario/v0.1/usuarios/info", accessToken);
        }


        initNavigationDrawer();
    }

    private class GetReq extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Carregando usuário", Toast.LENGTH_LONG).show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = params[0];
            String accessToken = params[1];


            HttpHandler sh = new HttpHandler();

            String req_url = Constants.URL_BASE + url;
            String jsonStr = sh.makeServiceCall(req_url, accessToken, apiKey);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject resp = new JSONObject(jsonStr);
                    return resp;
                } catch (JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            NavigationView navigationView = (NavigationView)findViewById(R.id.navView);
            View header = navigationView.getHeaderView(0);
            TextView tv_email = header.findViewById(R.id.tv_email);
            try {
                tv_email.setText(jsonObject.getString("nome-pessoa"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER && resultCode == RESULT_OK) {
            Offer offerRegistered = (Offer) data.getSerializableExtra("offerRegistered");
            //Do some manipulation with the object offerRegistered
            offers.get(0).add(offerRegistered);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    //Sidebar Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();
                Intent i;

                switch (id){
                    case R.id.cadastro:
                        i = new Intent(getApplicationContext(), RegistrationFormActivity.class);
                        startActivityForResult(i, REGISTER);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.catalogo:
                        Toast.makeText(getApplicationContext(),"Você já está em Catálogo",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        CookieManager cookieManager = CookieManager.getInstance();
                        cookieManager.removeAllCookies(null);
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
    }
}
