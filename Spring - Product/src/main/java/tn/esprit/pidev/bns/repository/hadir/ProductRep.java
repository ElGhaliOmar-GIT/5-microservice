package tn.esprit.pidev.bns.repository.hadir;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.bns.entity.hadir.Product;

import java.util.Optional;

@Repository
public interface ProductRep extends JpaRepository<Product,Integer> {
    //Optional<Product> findByLabel(String label);
}
