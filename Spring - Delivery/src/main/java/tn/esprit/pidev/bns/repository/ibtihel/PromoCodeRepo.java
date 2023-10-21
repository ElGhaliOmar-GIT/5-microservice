package tn.esprit.pidev.bns.repository.ibtihel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.bns.entity.ibtihel.PromoCode;

import java.util.Optional;

@Repository
public interface PromoCodeRepo extends JpaRepository<PromoCode, Integer> {
    Optional<PromoCode> findPromoCodeByCode(String code);
}
