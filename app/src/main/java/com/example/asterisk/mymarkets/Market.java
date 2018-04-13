package com.example.asterisk.mymarkets;

public class Market{

    private final String instrumentName;
    private final String displayOffer;

    Market(String instrumentName, String displayOffer) {
        this.instrumentName = instrumentName;
        this.displayOffer = displayOffer;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public String getDisplayOffer() {
        return displayOffer;
    }
}