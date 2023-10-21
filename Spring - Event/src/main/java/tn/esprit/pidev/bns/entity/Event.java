package tn.esprit.pidev.bns.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String title;
    private String description;
    private String content;
    private String img;

    private int userId;
    @OneToMany(cascade = CascadeType.ALL)
    List<EventComment> eventCommentList = new ArrayList<>();
}
