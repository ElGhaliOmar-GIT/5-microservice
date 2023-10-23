package tn.esprit.pidev.bns.repository.hadir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.pidev.bns.entity.hadir.Tva;

import java.util.Optional;

public interface TvaRep extends JpaRepository<Tva,Integer> {
    @Query("SELECT tauxTva FROM Tva ORDER BY dateCreation DESC")
    Optional<Double> findFirstTauxTva();
}
