package com.example.demo.entity.location;

import com.example.demo.entity.continent.Continent;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "covid", name = "location")
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

    @Column(
            name = "name",
            length = 120,
            nullable = false
    )
    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @ManyToOne
    @JoinColumn(
            name = "location_type_code",
            nullable = false
    )
    private LocationType locationType;

}
