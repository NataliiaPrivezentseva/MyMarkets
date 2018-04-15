package com.example.asterisk.mymarkets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MarketAdapter extends ArrayAdapter<Market> {

    MarketAdapter(Context context, ArrayList<Market> markets){
        super(context, 0, markets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.market_item, parent, false);
        }

        Market currentMarket = getItem(position);

        if (currentMarket != null) {
            TextView instrumentNameTextView =
                    listItemView.findViewById(R.id.instrument_name_text_view);
            instrumentNameTextView.setText(currentMarket.getInstrumentName());

            TextView displayOfferTextView =
                    listItemView.findViewById(R.id.display_offer_text_view);
            displayOfferTextView.setText(currentMarket.getDisplayOffer());
        }

        return listItemView;
    }
}