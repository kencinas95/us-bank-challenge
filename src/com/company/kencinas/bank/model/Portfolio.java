package com.company.kencinas.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<Instrument> instruments = new ArrayList<>();

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(final List<Instrument> instruments) {
        this.instruments = instruments;
    }
}
