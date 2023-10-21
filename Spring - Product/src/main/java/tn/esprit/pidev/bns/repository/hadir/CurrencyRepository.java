package tn.esprit.pidev.bns.repository.hadir;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.pidev.bns.entity.hadir.Currency;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository <Currency,Integer> {
    Optional<Currency> findByLabel(String label);
}
