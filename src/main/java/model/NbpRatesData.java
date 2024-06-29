package model;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Date;

public class NbpRatesData extends JSONObject {
    public BigDecimal getMid() {
        return BigDecimal.valueOf(this.getDouble("mid"));
    }

    public Date getEffectiveDate() {
        return Date.valueOf(this.getString("effectiveDate"));
    }
}
