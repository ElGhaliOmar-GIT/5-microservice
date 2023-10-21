package tn.esprit.pidev.bns.entity.ibtihel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class PurchaseOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;
    private String reference;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private int orderPrice;
    private int tax;

    @Temporal(TemporalType.DATE)
    private Date date;
    private String discountCode;
    private String address;
    private String phoneNumber;
    private String mail;




    @OneToOne
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    private Delivery delivery;

    @JsonIgnore
    @OneToOne
    private Payment payment;

   
}
