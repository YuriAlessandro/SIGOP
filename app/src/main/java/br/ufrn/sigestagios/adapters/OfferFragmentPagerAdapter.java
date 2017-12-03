package br.ufrn.sigestagios.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.sigestagios.fragments.OffersFragment;
import br.ufrn.sigestagios.models.Offer;

/**
 * Created by Gustavo on 19/10/2017.
 */

public class OfferFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 6;
    
    private String tabTitles[] = new String[] { "Estágios", "Monitoria", "Extensão",
                                                "Pesquisa", "Ação Associada", "Apoio Técnico"};
    private Context context;
    private List<List<Offer>> offers;
    private List<List<Offer>> copyOffers;

    public OfferFragmentPagerAdapter(FragmentManager fm, Context context, List<List<Offer>> offers) {
        super(fm);
        this.context = context;
        this.offers = offers;
        copyOffers = new ArrayList<List<Offer>>();
        for (int i = 0; i < offers.size(); i++) {
            ArrayList<Offer> list = new ArrayList<Offer>();
            list.addAll(offers.get(i));
            copyOffers.add(list);
        }
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

    @Override
    public int getItemPosition(Object object) {
       return POSITION_NONE;
    }

    public void filter(String query) {

        if(query.isEmpty()){
            for(int i = 0; i < offers.size(); i++) {
                offers.get(i).clear();
                offers.get(i).addAll(copyOffers.get(i));
            }
        } else {
            query = query.toLowerCase();
            for (int i = 0; i < copyOffers.size(); i++) {
                offers.get(i).clear();
                for (Offer offer : copyOffers.get(i)) {
                    if (offer.getDescription().toLowerCase().contains(query) || offer.getUnit().toLowerCase().contains(query)) {
                        offers.get(i).add(offer);
                    }
                }
            }
        }

        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        for(int i = 0; i < copyOffers.size(); i++) {
            copyOffers.get(i).clear();
            copyOffers.get(i).addAll(offers.get(i));
        }
        super.notifyDataSetChanged();
    }
}
