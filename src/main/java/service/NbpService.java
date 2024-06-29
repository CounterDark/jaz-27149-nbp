package service;

import exception.NoDataException;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import storage.NbpRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Service
public class NbpService {
    private NbpRepository nbpRepository;

    private RestTemplate restTemplate;

    public NbpService(NbpRepository nbpRepository, RestTemplate restTemplate)
    {
        this.nbpRepository = nbpRepository;
        this.restTemplate = restTemplate;
    }

    public Nbp queryCurrencyMidForSection(NbpRequestData requestData) {
        if (!validateCurrencyCode(requestData.currencyCode)) {
            throw new IllegalArgumentException("Bad currency code");
        }
        String code = requestData.currencyCode;
        Date startDate = requestData.sectionStart;
        Date endDate = requestData.sectionEnd;

        String url = "http://api.nbp.pl/api/exchangerates/rates/A/"+code+"/"+startDate+"/"+endDate+"/?format=json";
        NbpMidTableData json = restTemplate.getForEntity(url, NbpMidTableData.class).getBody();
        if (json == null) {
            throw new NoDataException("No json data from nbp");
        }
        String currencyCode = json.getCode();
        NbpRatesArray rates = json.getRates();
        BigDecimal midRatesFromSection = calculateMidRates(rates);
        Date sectionStart = rates.getRate(0).getEffectiveDate();
        Date sectionEnd = rates.getRate(rates.length()-1).getEffectiveDate();
        Nbp nbp = new Nbp();
        nbp.setCurrencyCode(currencyCode);
        nbp.setMidValue(midRatesFromSection);
        nbp.setSectionStart(sectionStart);
        nbp.setSectionEnd(sectionEnd);
        nbp.setQueryTime(new Timestamp(System.currentTimeMillis()));
        return nbpRepository.save(nbp);
    }

    private BigDecimal calculateMidRates(NbpRatesArray nbpRatesArray) {
        BigDecimal midRate = new BigDecimal(0);
        for (int i=0; i < nbpRatesArray.length()-1; i++) {
            NbpRatesData ratesData = nbpRatesArray.getRate(i);
            midRate.add(ratesData.getMid());
        }
        return midRate.divide(BigDecimal.valueOf(nbpRatesArray.length()));
    }
    private boolean validateCurrencyCode(String code) {
        if (code.length() != 3) {
            return false;
        }
        return true;
    }
}
