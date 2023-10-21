package tn.esprit.pidev.bns.entity.ibtihel;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCart;
    private int totalCart;






    @OneToMany(cascade = CascadeType.ALL, mappedBy="cart")
    private Set<CommandLine> commandLines;


}

