package tn.esprit.pidev.bns.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import tn.esprit.pidev.bns.entity.hadir.Currency;
import tn.esprit.pidev.bns.entity.hadir.CurrencyDTO;
import tn.esprit.pidev.bns.repository.hadir.CurrencyRepository;

import java.util.Collections;

@Component
public class ExchangeRateClient {
    @Autowired
    private CurrencyRepository currencyRepository;

    //RestTemplate restTemplate;

   /* public Rates getCurrencyResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("https://api.exchangerate.host/latest", HttpMethod.GET, entity, Rates.class).getBody();
    }http://data.fixer.io/api/latest?access_key=0a5f28039a528338c606d27c78c40e3b*/
   @Scheduled(fixedRate = 5 * 1000 * 60 * 60)
   private void getRatesTask() {
       try {
           RestTemplate restTemplate = new RestTemplate();
           CurrencyDTO forObject = restTemplate.getForObject("http://data.fixer.io/api/latest?access_key=0a5f28039a528338c606d27c78c40e3b", CurrencyDTO.class);
           if (currencyRepository.count() > 0) {
               // supprimer les anciennes valeurs
               currencyRepository.deleteAll();
           }
           forObject.getRates().forEach((key, value) -> {
               Currency currency = new Currency(key, value);
               this.currencyRepository.save(currency);
           });
       } catch (RestClientException ex) {
           System.out.println(ex.getMessage());
       }
   }


}
