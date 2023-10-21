package tn.esprit.pidev.bns.service.hadir;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;
import tn.esprit.pidev.bns.client.ExchangeRateClient;
import tn.esprit.pidev.bns.client.Rates;
import tn.esprit.pidev.bns.entity.hadir.*;
import tn.esprit.pidev.bns.entity.hadir.Currency;
import tn.esprit.pidev.bns.repository.hadir.*;
import tn.esprit.pidev.bns.serviceInterface.hadir.IProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRep productRep;
    @Autowired
    CategorieRep categorieRep;
    @Autowired
    ShopRep shopRep;
    @Autowired
    TvaRep tvaRep;

    @Autowired
    ExchangeRateClient exchangeRateClient;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    CategorieService categorieService;

    private final String LOCALHOST_IPV4 = "127.0.0.1";
    private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    @Override
    public List<Product> retrieveAllProducts() {
        return (List<Product>) productRep.findAll();
    }

    @Override
    public boolean updateProduct(Product product) {
        if (productRep.existsById(product.getIdProduct())) {
            productRep.save(product);
            return true;
        }
        return false;
    }

    @Override
    public Product addProduct(Product product, Integer idCategorie) {
        Category category = categorieRep.findById(idCategorie).orElseThrow(() -> new NotFoundException("Catégorie non trouvée"));
        category.getProducts();
        product.setCategory(category);

        categorieRep.save(category);
        return productRep.save(product);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> currencyList = this.currencyRepository.findAll();
        currencyList.sort(Comparator.comparing(Currency::getLabel));
        return currencyList;
    }

    @Override
    public Optional<Double> convert(ConversionCurrency conversionCurrency) {
        Optional<Currency> toOptional = this.currencyRepository.findByLabel(conversionCurrency.getTo().toUpperCase());
        Optional<Currency> fromOptional = this.currencyRepository.findByLabel(conversionCurrency.getFrom().toUpperCase());

        if(toOptional.isPresent() && fromOptional.isPresent()) {

            if(conversionCurrency.getValue() < 0) {
                return Optional.empty();
            }

            Currency to = toOptional.get();
            Currency from = fromOptional.get();
            Double toValue = to.getValueInEuros();
            Double fromValue = from.getValueInEuros();

            Double result = toValue * conversionCurrency.getValue() / fromValue;

            return Optional.of(result);

        }

        return Optional.empty();
    }

    @Override
    public Product retrieveProduct(Integer idProduct) {
        return productRep.findById(idProduct).orElse(null);
    }

    @Override
    public boolean removeProduct(Integer idProduct) {
        if (productRep.existsById(idProduct)) {
            productRep.deleteById(idProduct);
            return true;
        }
        return false;
    }

   /* @Override
    public String getClientIp(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://api.ipify.org", String.class);
        return response.getBody();
    }*/

    @Override
    public Map<String, String> getClientLocation(HttpServletRequest request) throws IOException, GeoIp2Exception {
        String clientIp = categorieService.getClientIp(request);
        File database = new File("C:/GeoLite2-City.mmdb");
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
        InetAddress ipAddress = InetAddress.getByName(clientIp);
        CityResponse response = reader.city(ipAddress);
        Map<String, String> location = new HashMap<>();
        location.put("country", response.getCountry().getName());
        location.put("region", response.getMostSpecificSubdivision().getName());
        location.put("city", response.getCity().getName());
        location.put("latitude", response.getLocation().getLatitude().toString());
        location.put("longitude", response.getLocation().getLongitude().toString());

        String countryCode = response.getCountry().getIsoCode();
        Locale locale = new Locale("", countryCode);
        java.util.Currency DeviseLoc = java.util.Currency.getInstance(locale);
        location.put("DeviseLoc", DeviseLoc.getCurrencyCode());
        return location;
    }






    /*@Override
    public void affcterProduitACategorie(Integer idCategorie, Integer idProduct) {
        Category category = categorieRep.findById(idCategorie)
                .orElseThrow(() -> new NotFoundException("Catégorie non trouvée"));

        Product product = productRep.findById(idProduct)
                .orElseThrow(() -> new NotFoundException("Produit non trouvé"));

        category.getProducts().add(product);
        product.setCategory(category);

        categorieRep.save(category);
        productRep.save(product);
    }*/


}
