package com.example.asterisk.mymarkets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MarketFragment extends Fragment{

    /**
     * URL for fetch information about markets
     */
    public static final String STRING_REQUEST_URL = "requestUrl";

    public MarketFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.market_list, container, false);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = rootView.findViewById(R.id.list);

        // Initiate an {@link AsyncTask} to perform the network request
        FragmentAsyncTask task = new FragmentAsyncTask(listView, getActivity());
        task.execute(getArguments().getString(MarketFragment.STRING_REQUEST_URL));

        return rootView;
    }

}
