package com.example.asterisk.mymarkets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class MarketGermanyFragment extends Fragment {

    /**
     * URL for fetch information about markets in Germany
     */
    private static final String REQUEST_URL_DE =
            "https://api.ig.com/deal/samples/markets/ANDROID_PHONE/de_DE/dem";


    public MarketGermanyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.market_list, container, false);

        //todo get list of markets from URL, it should be sorted
        final ArrayList<Market> markets = new ArrayList<>();

        markets.add(new Market("sdf", "1225441"));
        markets.add(new Market("jfnsurb", "1225.441"));
        markets.add(new Market("fer", "f515"));
        markets.add(new Market("dqqc4", "g1v88bf.t"));

        MarketAdapter adapter = new MarketAdapter(getActivity(), markets);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }
}