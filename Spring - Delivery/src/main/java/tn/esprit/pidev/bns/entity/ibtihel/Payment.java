package tn.esprit.pidev.bns.entity.ibtihel;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;


import javax.persistence.*;
import java.io.Serializable;
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idOrder;

    //stripe

    String customerId;
    int created;


    /*@OneToOne(cascade = CascadeType.ALL)
    private User user;*/

}
