package com.example.demo.entity;

import com.example.demo.composite_ids.ObservationDayId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "policy_observation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PolicyObservation {
    @EmbeddedId
    private ObservationDayId observationDayId;

    @Column(
            name = "stringency_index",
            nullable = false
    )
    private BigDecimal stringencyIndex;
}
