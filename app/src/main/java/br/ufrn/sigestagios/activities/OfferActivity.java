package br.ufrn.sigestagios.activities;


import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.sigestagios.adapters.OfferFragmentPagerAdapter;
import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.Offer;
import br.ufrn.sigestagios.utils.HttpHandler;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instanciando os 3 arrays
        for (int i = 0; i < 3; i++) {
            offers.add(new ArrayList<Offer>());
        }

        // Instanciando com exemplos
//        offers.get(0).add("Vaga SINFO");
//        offers.get(0).add("Vagas TRE");
//        offers.get(1).add("Monitoria FMC");
//        offers.get(1).add("Monitoria Cálculo");
//        offers.get(2).add("Assistente de hamburguer pra Johnnylee");
        //

//        new GetOffers().execute();

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.menu_main);
        setContentView(R.layout.activity_offer);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new OfferFragmentPagerAdapter(getSupportFragmentManager(),
                OfferActivity.this, offers);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //New toobar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Sidebar Menu
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initNavigationDrawer();
        // Configure and initialize recycler view
//        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new OffersListAdapter(offers);
//        mRecyclerView.setAdapter(mAdapter);

    }

//    private class GetOffers extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
//        }
//
//
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            HttpHandler sh = new HttpHandler();
//
//            String url = "https://jsonplaceholder.typicode.com/users";
//            String jsonStr = sh.makeServiceCall(url);
//
//
//            Log.e(TAG, "Response from url: " + jsonStr);
//            if (jsonStr != null) {
//                try {
//                    JSONArray s_offers = new JSONArray(jsonStr);
//
//                    for (int i = 0; i < s_offers.length(); i++) {
//                        JSONObject c = s_offers.getJSONObject(i);
//                        // Aqui deveríamos tratar todos os campos das ofertas.
//                        // Nesse momento de teste vou lidar apenas com um campo
//                        // para facilitar a remoção posteriormente
//
//                        String name = c.getString("name");
//                        offers.get(0).add(name);
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            Log.e(TAG, "Request finished");
//        }
//    }

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
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("sigaa.ufrn.br");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
    }
}
