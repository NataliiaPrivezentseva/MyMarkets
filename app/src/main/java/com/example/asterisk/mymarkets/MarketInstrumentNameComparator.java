package com.example.asterisk.mymarkets;

import java.util.Comparator;

public class MarketInstrumentNameComparator implements Comparator<Market> {
    @Override
    public int compare(Market market1, Market market2) {

        String instrumentName1 = market1.getInstrumentName();
        String instrumentName2 = market2.getInstrumentName();

        //noinspection StringEquality
        if(instrumentName1 == instrumentName2){
            return 0;
        }
        if(instrumentName1 == null){
            return -1;
        }
        if(instrumentName2 == null){
            return 1;
        }
        return instrumentName1.compareToIgnoreCase(instrumentName2);
    }
}