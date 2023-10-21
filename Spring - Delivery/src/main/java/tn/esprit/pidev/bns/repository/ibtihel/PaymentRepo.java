package tn.esprit.pidev.bns.repository.ibtihel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.bns.entity.ibtihel.Payment;
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
