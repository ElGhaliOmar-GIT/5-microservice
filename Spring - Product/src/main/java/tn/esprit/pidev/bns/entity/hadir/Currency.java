package tn.esprit.pidev.bns.entity.hadir;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Currency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCUr;
    private String label;
    private double valueInEuros ;

    public Currency(String label, double valueInEuros) {
        this.label = label;
        this.valueInEuros = valueInEuros;
    }
}
