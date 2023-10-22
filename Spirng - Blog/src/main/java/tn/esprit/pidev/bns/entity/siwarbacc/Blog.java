package tn.esprit.pidev.bns.entity.siwarbacc;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBlog;
    private String title;
    private String content;
    private LocalDate publicationDate = LocalDate.now();
    @PrePersist
    public void beforePersist() {
        publicationDate = LocalDate.now();
    }

    private boolean isValid;

}
