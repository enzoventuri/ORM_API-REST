package com.example.demo.entity.observation;

import com.example.demo.composite_ids.ObservationDayId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(schema = "covid", name = "policy_observation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PolicyObservation {
    @EmbeddedId
    private ObservationDayId observationDayId;

    @OneToOne
    @MapsId
    @JoinColumns({
            @JoinColumn(
                    name = "location_id",
                    referencedColumnName = "location_id"
            ),
            @JoinColumn(
                    name = "observation_date",
                    referencedColumnName = "observation_date"
            )
    })
    private ObservationDay observationDay;

    @Column(
            name = "stringency_index",
            nullable = false
    )
    private BigDecimal stringencyIndex;
}
