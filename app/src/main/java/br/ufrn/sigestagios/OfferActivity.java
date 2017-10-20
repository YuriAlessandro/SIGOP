package br.ufrn.sigestagios;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class OfferActivity extends AppCompatActivity {

//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    List<List<String>> offers = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instanciando os 3 arrays
        for (int i = 0; i < 3; i++) {
            offers.add(new ArrayList<String>());
        }

        // Instanciando com exemplos
        offers.get(0).add("Vaga SINFO");
        offers.get(0).add("Vagas TRE");
        offers.get(1).add("Monitoria FMC");
        offers.get(1).add("Monitoria Cálculo");
        offers.get(2).add("Assistente de hamburguer pra Johnnylee");
        //

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        OfferFragmentPagerAdapter pagerAdapter= new OfferFragmentPagerAdapter(getSupportFragmentManager(),
                OfferActivity.this, offers);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Configure and initialize recycler view
//        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new MyAdapter(offers);
//        mRecyclerView.setAdapter(mAdapter);

    }

}
