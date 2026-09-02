package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(
            name = "iso_code",
            length = 12,
            nullable = false
    )
    private String isoCode;

    @JoinColumn(name = "continent_id")
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Continent continent;

    @Column(
            name = "name",
            length = 120,
            nullable = false
    )
    private String name;


    @Column(
            name = "location_type_code",
            length = 30,
            nullable = false
    )
    private String locationTypeCode;

}
