package tn.esprit.pidev.bns.repository.ibtihel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.bns.entity.ibtihel.PurchaseOrder;

import java.util.List;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Integer> {

}
