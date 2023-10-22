package tn.esprit.pidev.bns.entity.siwarbacc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idForum;
    private String title;
    private String content;

    private LocalDate publicationDate = LocalDate.now();
    @PrePersist
    public void beforePersist() {
        publicationDate = LocalDate.now();
    }



    @JsonIgnore
    @OneToMany(mappedBy="forum")
    private Set<Comment> comments;
}
