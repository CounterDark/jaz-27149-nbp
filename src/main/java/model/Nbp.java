package model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Nbp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", example = "1")
    private Long id;

    @Schema(name = "currencyCode", example = "USD")
    private String currencyCode;

    @Schema(name = "sectionStart", example = "1999-11-20")
    private Date sectionStart;

    @Schema(name = "sectionEnd", example = "2220-01-12")
    private Date sectionEnd;

    @Schema(name = "midValue", example = "1.2312")
    private BigDecimal midValue;

    @Schema(name = "queryTime", example = "1999-11-11 17:45:11")
    private Timestamp queryTime;

    public Nbp() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getSectionStart() {
        return sectionStart;
    }

    public void setSectionStart(Date sectionStart) {
        this.sectionStart = sectionStart;
    }

    public Date getSectionEnd() {
        return sectionEnd;
    }

    public void setSectionEnd(Date sectionEnd) {
        this.sectionEnd = sectionEnd;
    }

    public BigDecimal getMidValue() {
        return midValue;
    }

    public void setMidValue(BigDecimal midValue) {
        this.midValue = midValue;
    }

    public Timestamp getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Timestamp queryTime) {
        this.queryTime = queryTime;
    }
}
