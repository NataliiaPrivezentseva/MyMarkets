package com.example.asterisk.mymarkets;

import android.os.Bundle;

import java.util.HashMap;

public class MarketFragmentFactory {

    private static final HashMap<String, String> marketsUrls;

    static {
        marketsUrls = new HashMap<>();
        marketsUrls.put("UK", "https://api.ig.com/deal/samples/markets/ANDROID_PHONE/en_GB/igi");
        marketsUrls.put("Germany", "https://api.ig.com/deal/samples/markets/ANDROID_PHONE/de_DE/dem");
        marketsUrls.put("France", "https://api.ig.com/deal/samples/markets/ANDROID_PHONE/fr_FR/frm");
    }

    public static MarketFragment getMarketFragment(String country){
        if (marketsUrls.containsKey(country)){
            MarketFragment marketFragment = new MarketFragment();
            Bundle bundle = new Bundle();
            bundle.putString(MarketFragment.STRING_REQUEST_URL,marketsUrls.get(country));
            marketFragment.setArguments(bundle);
            return marketFragment;
        }
        // exception
        return null;
    }

}
