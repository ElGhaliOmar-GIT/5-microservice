package tn.esprit.pidev.bns.entity.ibtihel;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDelivery;
    private String address;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Temporal(TemporalType.DATE)
    private  Date arrivalDate;

    private int totalPrice;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="delivery")
    private Set<PurchaseOrder> purchaseOrders;


   /* @ManyToOne
    private Deliverer deliverer;*/
}
