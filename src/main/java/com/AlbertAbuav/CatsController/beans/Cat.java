package com.AlbertAbuav.CatsController.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(columnDefinition = "FLOAT(2,1)")
    private float weight;

    @OneToMany(cascade = CascadeType.ALL)  // ==> One Cat have Many Toys
    @JoinTable(name = "THE_CAT_TOYS", joinColumns = @JoinColumn(name = "THE_CAT_ID"),
            inverseJoinColumns = @JoinColumn(name = "THE_TOY_ID")
    )
    @Singular // ==> Works with Builder and initializes the list and let us insert a single TOY to a CAT each time
    private List<Toy> toys = new ArrayList<>();

}
