package tn.esprit.pidev.bns.repository.ibtihel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.bns.entity.ibtihel.Delivery;

@Repository
public interface DeliveryRepo  extends JpaRepository<Delivery, Integer> {

    @Query(value="select count(*) from delivery d where d.delivery_Date > cast((now()) as date) and d.deliverer_id = ?1",nativeQuery=true)
    int notstarted(int id);


    @Query(value="select count(*) from delivery d where d.arrival_Date < cast((now()) as date) and d.deliverer_id = ?1",nativeQuery=true)
    int delivered(int id);

    @Query(value="select count(*) from delivery d "
            + "where d.delivery_Date< cast((now()) as date) "
            + "and d.arrival_Date > cast((now()) as date) and d.deliverer_id = ?1",nativeQuery=true)
    int inProgress(int id);


    @Query(value="select count(*) from delivery d where d.deliverer_id = ?1",nativeQuery=true)
    int total(int id);

}
