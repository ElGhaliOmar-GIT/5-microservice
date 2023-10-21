package tn.esprit.pidev.bns.entity.hadir;

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
public class Favorite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFavorite;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Product> products;
}
