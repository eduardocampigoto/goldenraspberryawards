package com.goldenraspberry.awards.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Title implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int year;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "producers_titles",
            joinColumns = {@JoinColumn(name = "title_id")},
            inverseJoinColumns = {@JoinColumn(name = "producer_id")}
    )
    private Set<Producer> producers;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "studios_titles",
            joinColumns = {@JoinColumn(name = "title_id")},
            inverseJoinColumns = {@JoinColumn(name = "studio_id")}
    )
    private Set<Studio> studios;

    private String winner;

    public boolean isWinner() {
        return Objects.equals(winner, "yes");
    }

}

