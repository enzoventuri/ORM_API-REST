package com.example.demo.entity.location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(schema = "covid", name = "location_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LocationType {
    @Id
    @Column(
            name = "location_type_code",
            length = 30,
            nullable = false
    )
    private String locationTypeCode;

    @Column(
            name = "description",
            length = 150,
            nullable = false
    )
    private String description;

}
