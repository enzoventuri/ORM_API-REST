package com.example.demo.entity.continent;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "covid", name = "continent")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continent_id")
    private Short continent_id;

    @Column(
            name = "name",
            nullable = false,
            length = 40
    )
    private String name;

}
