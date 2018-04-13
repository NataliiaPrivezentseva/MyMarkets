package com.example.asterisk.mymarkets;

import android.os.Bundle;

import java.util.LinkedHashMap;

public class MarketFragmentFactory {

    static final LinkedHashMap<String, String> MARKETS_URLS;

    static {
        MARKETS_URLS = new LinkedHashMap<>();
        MARKETS_URLS.put("UK", "https://api.ig.com/deal/samples/markets/ANDROID_PHONE/en_GB/igi");
        MARKETS_URLS.put("Germany", "https://api.ig.com/deal/samples/markets/ANDROID_PHONE/de_DE/dem");
        MARKETS_URLS.put("France", "https://api.ig.com/deal/samples/markets/ANDROID_PHONE/fr_FR/frm");
    }

    public static MarketFragment getMarketFragment(String country){
        if (MARKETS_URLS.containsKey(country)){
            MarketFragment marketFragment = new MarketFragment();
            Bundle bundle = new Bundle();
            bundle.putString(MarketFragment.STRING_REQUEST_URL, MARKETS_URLS.get(country));
            marketFragment.setArguments(bundle);
            return marketFragment;
        }
        throw new NullPointerException("There is no markets for country " + country);
    }
}