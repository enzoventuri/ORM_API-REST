package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "continent")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continent_id")
    private Short id;

    @Column(
            name = "name",
            length = 40,
            nullable = false
    )
    private String name;

}
