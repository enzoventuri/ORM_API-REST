package com.example.demo.entity.testunit;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "covid", name = "test_unit")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TestUnit {
    @Id
    @Column(
            name = "test_unit_code",
            nullable = false,
            length = 30
    )
    private String testUnitCode;

    @Column(
            name = "description",
            nullable = false,
            length = 100
    )
    private String description;
}
