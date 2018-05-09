package br.ufrn.sigestagios.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.adapters.OfferFragmentPagerAdapter;
import br.ufrn.sigestagios.database.OfferDBContract.OfferEntry;
import br.ufrn.sigestagios.database.OfferDatabaseController;
import br.ufrn.sigestagios.models.AssociatedAction;
import br.ufrn.sigestagios.models.Extension;
import br.ufrn.sigestagios.models.Internship;
import br.ufrn.sigestagios.models.Offer;
import br.ufrn.sigestagios.models.ResearchGrant;
import br.ufrn.sigestagios.models.SupportService;
import br.ufrn.sigestagios.models.TeacherAssistant;
import br.ufrn.sigestagios.models.User;
import br.ufrn.sigestagios.utils.Constants;
import br.ufrn.sigestagios.utils.HttpHandler;

public class OfferActivity extends AppCompatActivity {
    private User loggedUser;

    OfferDatabaseController databaseController;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    OfferFragmentPagerAdapter pagerAdapter;

    SwipeRefreshLayout mSwipeRefreshLayout;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;

    String accessToken;

    List<List<Offer>> offers = new ArrayList<List<Offer>>();

    private String TAG = OfferActivity.class.getSimpleName();

    private static final int REGISTER = 0;

    private String apiKey = "iZOuoL4kPb1xuJUeLD3AGwU3xKCcJ5uwrctBfwX6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        for (int i = 0; i < 6; i++) {
            offers.add(new ArrayList<Offer>());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new OfferFragmentPagerAdapter(getSupportFragmentManager(), offers);
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

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = this.getSharedPreferences("user_info", 0);
        accessToken = preferences.getString(Constants.KEY_ACCESS_TOKEN, null);

        if(accessToken != null){
            new GetLoggedUser().execute("usuario/v0.1/usuarios/info", accessToken);

            // Get offers from SIGAA
            Toast.makeText(getApplicationContext(), "Carregando ofertas", Toast.LENGTH_LONG).show();
            new GetInternshipFromSigaa().execute(accessToken);
        }

        // Database Controller
        databaseController = new OfferDatabaseController(this);

        // Get offers from Database
        new DatabasePopulator(offers, pagerAdapter, databaseController).execute();

        //Swipe to refresh
        initNavigationDrawer();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.search_bar);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                pagerAdapter.filter(query);
                return false;
            }
        });

        return true;
    }


    private class GetLoggedUser extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = params[0];
            String accessToken = params[1];

            HttpHandler sh = new HttpHandler();

            String req_url = Constants.URL_BASE + url;
            String jsonStr = sh.makeServiceCall(req_url, accessToken, apiKey);

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
            try {
                loggedUser = new User(jsonObject.getLong("id-usario"),
                        jsonObject.getLong("id-unidade"),
                        jsonObject.getLong("id-foto"),
                        jsonObject.getBoolean("ativo"),
                        jsonObject.getString("login"),
                        jsonObject.getString("nome-pessoa"),
                        jsonObject.getString("cpf-cnpj"),
                        jsonObject.getString("email"),
                        jsonObject.getString("chave-foto"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    private class DatabasePopulator extends AsyncTask<Void, Void, Void> {

        List<List<Offer>> offers;
        OfferFragmentPagerAdapter pagerAdapter;
        OfferDatabaseController databaseController;

        DatabasePopulator (List<List<Offer>> offers,
                           OfferFragmentPagerAdapter pagerAdapter,
                           OfferDatabaseController databaseController) {
            this.offers = offers;
            this.pagerAdapter = pagerAdapter;
            this.databaseController = databaseController;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Cursor cursor = databaseController.retrieveOffers();
            while (cursor != null && cursor.moveToNext()) {

                Offer temp = new Internship(
                        cursor.getString(cursor.getColumnIndex(OfferEntry.DESCRICAO)),
                        cursor.getString(cursor.getColumnIndex(OfferEntry.EMAIL)),
                        cursor.getString(cursor.getColumnIndex(OfferEntry.UNIDADE)),
                        cursor.getString(cursor.getColumnIndex(OfferEntry.RESPONSAVEL)),
                        cursor.getInt(cursor.getColumnIndex(OfferEntry.VAGAS)),
                        cursor.getInt(cursor.getColumnIndex(OfferEntry.VALOR)),
                        cursor.getInt(cursor.getColumnIndex(OfferEntry.AUXTRANSP)),
                        cursor.getString(cursor.getColumnIndex(OfferEntry.FIMOFERTA)),
                        cursor.getString(cursor.getColumnIndex(OfferEntry.TITULO)));
                offers.get(0).add(temp);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER && resultCode == RESULT_OK) {
            Internship internshipRegistered = (Internship) data.getSerializableExtra("offerRegistered");

            databaseController.insertOffer(internshipRegistered);
            offers.get(0).add(internshipRegistered);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    //Sidebar Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.myProfile){
            Intent i = new Intent(getApplicationContext(), UserProfile.class);
            startActivity(i);
            return true;
        }
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navView);

        navigationView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        int[][] state = new int[][] {
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed

        };

        int[] color = new int[] {
                getResources().getColor(R.color.colorPrimaryText),
                getResources().getColor(R.color.colorPrimaryText),
                getResources().getColor(R.color.colorPrimaryText),
                getResources().getColor(R.color.colorAccent)
        };

        ColorStateList csl = new ColorStateList(state, color);

        // FOR NAVIGATION VIEW ITEM ICON COLOR
        int[][] states = new int[][] {
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed

        };

        int[] colors = new int[] {
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent)
        };

        ColorStateList csl2 = new ColorStateList(states, colors);

        navigationView.setItemTextColor(csl);
        navigationView.setItemIconTintList(csl2);
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
                    case R.id.logout:
                        CookieManager cookieManager = CookieManager.getInstance();
                        cookieManager.removeAllCookies(null);
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.about:
                        i = new Intent(getApplicationContext(), AboutActivity.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
    }
    //Update all the tabs when swipe
    public void refreshItems() {
        this.clearOffers();
        Toast.makeText(getApplicationContext(), "Carregando ofertas", Toast.LENGTH_LONG).show();

        // Get offers from Database (Internships)
        new DatabasePopulator(offers, pagerAdapter, databaseController).execute();

        //get the offers for Associated Actions
        new GetInternshipFromSigaa().execute(accessToken);
    }

    public void clearOffers(){
        //clear all tabs
        for (int i = 0; i < 6; i++){
            offers.get(i).clear();
        }
        pagerAdapter.notifyDataSetChanged();

    }

    private class GetInternshipFromSigaa extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String url_extensions = "estagio/v0.1/ofertas-estagios?limit=1";
            String accessToken = params[0];

            HttpHandler sh = new HttpHandler();

            String req_url = Constants.URL_BASE + url_extensions;
            String jsonStr = sh.makeServiceCall(req_url, accessToken, apiKey);

            if (jsonStr == null) return null;
            try {
                JSONArray internships = new JSONArray(jsonStr);

                for(int i = 0; i < internships.length(); i++){
                    JSONObject opportunity = internships.getJSONObject(i);

                    int idOffer = opportunity.getInt("id-oferta-estagio");
//                    int idConcedenteEstagio = opportunity.getInt("id-concedente-estagio");
                    String title = opportunity.getString("titulo");
                    String description = opportunity.getString("descricao");
                    int vacancies = opportunity.getInt("qtd-vagas");
                    int value = (int) opportunity.getDouble("valor-bolsa");
                    int tranpAux = (int) opportunity.getDouble("valor-aux-transporte");

                    String endOffer = String.valueOf(opportunity.getLong("data-fim-publicacao"));

                    Offer internship = new Internship(description, title, vacancies, value, tranpAux, endOffer);

                    offers.get(0).add(internship);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pagerAdapter.notifyDataSetChanged();

            new GetAssistantsFromSigaa().execute(accessToken);
            new GetExtensionFromSigaa().execute(accessToken);
            new GetAssociatedActionFromSigaa().execute(accessToken);
            new GetResearchsFromSigaa().execute(accessToken);
            new GetSupportFromSigaa().execute(accessToken);
        }
    }

    private class GetAssistantsFromSigaa extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String url_extensions = "monitoria/v0.1/oportunidades-bolsas?limit=100&ano=2018";
            String accessToken = params[0];

            HttpHandler sh = new HttpHandler();

            String req_url = Constants.URL_BASE + url_extensions;
            String jsonStr = sh.makeServiceCall(req_url, accessToken, apiKey);

            if (jsonStr == null) return null;
            try {
                JSONArray assistants = new JSONArray(jsonStr);

                Log.i(TAG, String.valueOf(assistants.length()));
                for(int i = 0; i < assistants.length(); i++){
                    JSONObject opportunity = assistants.getJSONObject(i);

                    String description = opportunity.getString("descricao");

                    if (description.equals("null") || description == null){
                        description = "Título não informado";
                    }

                    String term = opportunity.getString("unidade");
                    int idTerm = opportunity.getInt("id-unidade");
                    String email = opportunity.getString("email-responsavel");
                    String responsible = opportunity.getString("responsavel");
                    String cpf_cnpj = opportunity.getString("cpf-cnpj").toString();
                    int idProject = opportunity.getInt("id-projeto");
                    int idProjectTA = opportunity.getInt("id-projeto-monitoria");
                    int year = opportunity.getInt("ano");
                    int positionsRemunerated = opportunity.getInt("vagas-remuneradas");
                    int positionsVolunteers = opportunity.getInt("vagas-voluntarias");


                    Offer teacherAssistant = new TeacherAssistant(description, term, idTerm, email, year,
                            cpf_cnpj, idProject, idProjectTA, responsible, positionsRemunerated, positionsVolunteers);

                    offers.get(1).add(teacherAssistant);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    private class GetExtensionFromSigaa extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String url_extensions = "extensao/v0.1/oportunidades-bolsas?limit=100&ano=2018";
            String accessToken = params[0];

            HttpHandler sh = new HttpHandler();

            String req_url = Constants.URL_BASE + url_extensions;
            String jsonStr = sh.makeServiceCall(req_url, accessToken, apiKey);

            if (jsonStr == null) return null;
            try {
                JSONArray extesions = new JSONArray(jsonStr);

                for(int i = 0; i < extesions.length(); i++){
                    JSONObject opportunity = extesions.getJSONObject(i);

                    String description = opportunity.getString("descricao");
                    String term = opportunity.getString("unidade");
                    int idTerm = opportunity.getInt("id-unidade");
                    String email = opportunity.getString("email-responsavel");
                    int positionsRemunerated = opportunity.getInt("vagas-remuneradas");
                    String responsible = opportunity.getString("responsavel");
                    long cpf_cnpj = opportunity.getLong("cpf-cnpj");
                    int idProject = opportunity.getInt("id-projeto");
                    int year = opportunity.getInt("ano");
                    int idProjectExtension = opportunity.getInt("id-atividade-extensao");


                    Offer extension = new Extension(description, term, idTerm, email, year,
                                                    cpf_cnpj, responsible, positionsRemunerated,
                                                    idProject, idProjectExtension);

                    offers.get(2).add(extension);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    private class GetResearchsFromSigaa extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String url_extensions = "pesquisa/v0.1/oportunidades-bolsas?limit=100&ano=2018";
            String accessToken = params[0];

            HttpHandler sh = new HttpHandler();

            String req_url = Constants.URL_BASE + url_extensions;
            String jsonStr = sh.makeServiceCall(req_url, accessToken, apiKey);

            if (jsonStr == null) return null;
            try {
                JSONArray researchs = new JSONArray(jsonStr);

                for(int i = 0; i < researchs.length(); i++){
                    JSONObject opportunity = researchs.getJSONObject(i);

                    String description = opportunity.getString("descricao");
                    String term = opportunity.getString("unidade");
                    int idTerm = opportunity.getInt("id-unidade");
                    String email = opportunity.getString("email-responsavel");
                    int positionsRemunerated = opportunity.getInt("vagas-remuneradas");
                    String responsible = opportunity.getString("responsavel");
                    String cpf_cnpj = opportunity.getString("cpf-cnpj-responsavel").toString();
                    int idProject = opportunity.getInt("id-projeto");
                    int year = opportunity.getInt("ano");
                    int numberPositions = opportunity.getInt("numero-vagas");
                    int idWorkPlan = opportunity.getInt("id-plano-trabalho");


                    Offer research = new ResearchGrant(description, term, idTerm, email, year,
                                                        cpf_cnpj, responsible, positionsRemunerated,
                                                        idProject, numberPositions, idWorkPlan);

                    offers.get(3).add(research);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    private class GetAssociatedActionFromSigaa extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String url_bolsas = "acao-associada/v0.1/oportunidades-bolsas?limit=100&ano=2018";
            String accessToken = params[0];

            HttpHandler sh = new HttpHandler();

            String req_url = Constants.URL_BASE + url_bolsas;
            String jsonStr = sh.makeServiceCall(req_url, accessToken, apiKey);

            if (jsonStr == null) return null;
            try {
                JSONArray bolsas = new JSONArray(jsonStr);

                for(int i = 0; i < bolsas.length(); i++){
                    JSONObject bolsa = bolsas.getJSONObject(i);

                    String description = bolsa.getString("descricao");
                    String term = bolsa.getString("unidade");
                    int idTerm = bolsa.getInt("id-unidade");
                    String email = bolsa.getString("email-responsavel");
                    int year = bolsa.getInt("ano");
                    long cpf_cnpj = bolsa.getLong("cpf-cnpj");
                    String responsible = bolsa.getString("responsavel");
                    int positionRemunareted = bolsa.getInt("vagas-remuneradas");
                    int idProject = bolsa.getInt("id-projeto");

                    Offer offer = new AssociatedAction(description, term, idTerm, email,
                                                        year, cpf_cnpj, responsible, positionRemunareted,
                                                        idProject);

                    offers.get(4).add(offer);
//                    Log.i(TAG, "NEW THING ADDED " + description);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    private class GetSupportFromSigaa extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String url_extensions = "bolsa-apoio-tecnico/v0.1/oportunidades?limit=100";
            String accessToken = params[0];

            HttpHandler sh = new HttpHandler();

            String req_url = Constants.URL_BASE + url_extensions;
            String jsonStr = sh.makeServiceCall(req_url, accessToken, apiKey);

            if (jsonStr == null) return null;
            try {
                JSONArray opportunities = new JSONArray(jsonStr);

                for(int i = 0; i < opportunities.length(); i++){
                    JSONObject opportunity = opportunities.getJSONObject(i);

                    String description = opportunity.getString("descricao");
                    String term = opportunity.getString("unidade");
                    int idTerm = opportunity.getInt("id-unidade");
                    String email = opportunity.getString("email");
                    String abbrevTerm = opportunity.getString("sigla-unidade");
                    int idOpportunity = opportunity.getInt("id-oportunidade");


                    Offer supportService = new SupportService(description, term, idTerm, email,
                                                        abbrevTerm, idOpportunity);

                    offers.get(5).add(supportService);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pagerAdapter.notifyDataSetChanged();
        }
    }
}
