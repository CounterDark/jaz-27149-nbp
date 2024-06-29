package model;

import org.json.JSONArray;
import org.json.JSONObject;

public class NbpMidTableData extends JSONObject {
    public String getCurrency() {
        return String.valueOf(this.get("currency"));
    }

    public String getCode() {
        return String.valueOf(this.get("code"));
    }

    public NbpRatesArray getRates() {
        return (NbpRatesArray) this.getJSONArray("rates");
    }
}
