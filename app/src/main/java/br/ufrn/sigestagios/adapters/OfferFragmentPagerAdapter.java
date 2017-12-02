package br.ufrn.sigestagios.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import br.ufrn.sigestagios.fragments.OffersFragment;
import br.ufrn.sigestagios.models.Offer;

/**
 * Created by Gustavo on 19/10/2017.
 */

public class OfferFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 6;
    
    private String tabTitles[] = new String[] { "Estágios", "Ação Associada", "Monitoria", "Extensão",
                                                "Apoio Técnico", "Pesquisa" };
    // Terminar isso
    private Class displayActivities[] = new Class[] {};
    private List<List<Offer>> offers;

    public OfferFragmentPagerAdapter(FragmentManager fm, List<List<Offer>> offers) {
        super(fm);
        this.offers = offers;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return OffersFragment.newInstance(position + 1, offers.get(position), displayActivities[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
       return POSITION_NONE;
    }
}
