package tn.esprit.pidev.bns.entity.siwarbacc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveAction;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;
    private String content;

    private LocalDate createdAt = LocalDate.now();

    @PrePersist
    public void beforePersist() {
        createdAt = LocalDate.now();
    }

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "idForum", nullable = true)
        private Forum forum;


    }
