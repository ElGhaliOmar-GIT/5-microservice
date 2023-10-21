package tn.esprit.pidev.bns.repository.hadir;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.bns.entity.hadir.Currency;
import tn.esprit.pidev.bns.entity.hadir.Shop;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRep extends JpaRepository<Shop,Integer> {
    List<Shop> findByName(String name);
}
