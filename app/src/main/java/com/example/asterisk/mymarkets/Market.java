package com.example.asterisk.mymarkets;

import android.support.annotation.NonNull;

import java.util.Comparator;

public class Market implements Comparable<Market>{

    private String instrumentName;
    private String displayOffer;

    public Market(String instrumentName, String displayOffer) {
        this.instrumentName = instrumentName;
        this.displayOffer = displayOffer;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public String getDisplayOffer() {
        return displayOffer;
    }

    @Override
    public int compareTo(Market market) {
        return this.getInstrumentName().compareTo(market.getInstrumentName());
    }

    /**
     * Comparator to sort list of markets in order of instrumentName
     */
    public static Comparator<Market> MarketInstrumentNameComparator = new Comparator<Market>() {

        public int compare(Market market1, Market market2) {

            String instrumentName1 = market1.getInstrumentName().toLowerCase();
            String instrumentName2 = market2.getInstrumentName().toLowerCase();

            return instrumentName1.compareTo(instrumentName2);
        }
    };
}