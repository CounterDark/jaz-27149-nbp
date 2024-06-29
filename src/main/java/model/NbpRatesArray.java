package model;

import org.json.JSONArray;

public class NbpRatesArray extends JSONArray {
    public NbpRatesData getRate(int index) {
        if (index < 0 || index > this.length()-1) {
            throw new NullPointerException("Rate not exists at index");
        }

        return (NbpRatesData)this.get(index);
    }
}
