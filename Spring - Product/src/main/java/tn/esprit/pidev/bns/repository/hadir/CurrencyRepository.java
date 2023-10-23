package tn.esprit.pidev.bns.repository.hadir;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev.bns.entity.hadir.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository <Currency,Integer> {
    Optional<Currency> findByLabel(String label);
}
