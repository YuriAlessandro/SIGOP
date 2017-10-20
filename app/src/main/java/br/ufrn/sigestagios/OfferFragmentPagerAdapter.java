package br.ufrn.sigestagios;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Gustavo on 19/10/2017.
 */

public class OfferFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Estágios", "Bolsas", "Outros" };
    private Context context;
    private List<List<String>> offers;

    public OfferFragmentPagerAdapter(FragmentManager fm, Context context, List<List<String>> offers) {
        super(fm);
        this.context = context;
        this.offers = offers;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return OffersFragment.newInstance(position + 1, offers.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
