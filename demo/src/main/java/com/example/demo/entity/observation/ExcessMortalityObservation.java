package com.example.demo.entity.observation;

import com.example.demo.composite_ids.ObservationDayId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(schema = "covid", name = "excess_mortality_observation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExcessMortalityObservation {
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
            name = "excess_mortality_cumulative_absolute"
    )
    private BigDecimal excessMortalityCumulativeAbsolute;

    @Column(
            name = "excess_mortality_cumulative"
    )
    private BigDecimal excessMortalityCumulative;

    @Column(
            name = "excess_mortality"
    )
    private BigDecimal excessMortality;

    @Column(
            name = "excess_mortality_cumulative_per_million"
    )
    private BigDecimal excessMortalityCumulativePerMillion;

}
