package br.ufrn.sigestagios.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.adapters.OffersListAdapter;
import br.ufrn.sigestagios.models.Offer;

/**
 * Created by Gustavo on 19/10/2017.
 */

public class OffersFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String OFFERS = "OFFERS";

    private int mPage;
    List<Offer> offers;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public OffersFragment() {
        // Required empty public constructor
    }

    // No futuro a lista será inserida com putSerializable
    public static OffersFragment newInstance(int page, List<Offer> strings) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putSerializable(OFFERS, (ArrayList<Offer>) strings);

        OffersFragment fragment = new OffersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        offers = (List<Offer>) getArguments().getSerializable(OFFERS);

        mAdapter = new OffersListAdapter(offers);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_recycle_view, container, false);

        // Configure and initialize recycler view
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}