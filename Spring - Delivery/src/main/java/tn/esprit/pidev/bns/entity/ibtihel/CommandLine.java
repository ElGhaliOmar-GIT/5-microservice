package tn.esprit.pidev.bns.entity.ibtihel;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CommandLine implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommandeLine;
    private int quantity;



   /* @ManyToMany(cascade = CascadeType.ALL)
    private Set<Product> products;*/

   @JsonIgnore
   @ManyToOne (cascade = CascadeType.ALL)
   private Cart cart;
}
//idproduit idpanier qte commandline