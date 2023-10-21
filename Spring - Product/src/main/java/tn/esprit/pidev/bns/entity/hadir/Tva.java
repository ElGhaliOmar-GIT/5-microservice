package tn.esprit.pidev.bns.entity.hadir;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Tva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTva;
    double tauxTva;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date dateCreation;
}
