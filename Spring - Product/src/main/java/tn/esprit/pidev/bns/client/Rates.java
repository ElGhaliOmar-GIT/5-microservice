package tn.esprit.pidev.bns.client;

import java.util.Map;
import java.util.Objects;

public class Rates {

    private final Map<String,Long> rates;
    private final String base;

    public Rates(Map<String, Long> rates, String base) {
        this.rates = rates;
        this.base = base;
    }

    public Map<String, Long> getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rates rates1 = (Rates) o;
        return Objects.equals(rates, rates1.rates) && Objects.equals(base, rates1.base);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rates, base);
    }
}
