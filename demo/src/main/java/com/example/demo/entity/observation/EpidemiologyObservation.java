package com.example.demo.entity.observation;

import com.example.demo.composite_ids.ObservationDayId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Represents the Epidemiology Observation Entity Class
 */

@Schema(description = "Represents the Epidemiology Observation")
@Entity
@Table(schema = "covid", name = "epidemiology_observation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EpidemiologyObservation {
    /**
     * Composite PK for Epidemiology Observation that has Location ID and Observation Date
     */
    @Schema(description = "Composite PK of the Observation")
    @EmbeddedId
    private ObservationDayId id;

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

    /**
     * Total Virus Cases
     */
    @Schema(description = "Total Virus Cases", example = "10000")
    @Column(
            name = "total_cases"
    )
    private BigDecimal totalCases;

    /**
     * New Virus Cases
     */
    @Schema(description = "New Virus Cases", example = "10000")
    @Column(
            name = "new_cases"
    )
    private BigDecimal newCases;

    @Column(
            name = "new_cases_smoothed"
    )
    private BigDecimal newCasesSmooth;

    @Column(
            name = "new_deaths"
    )
    private BigDecimal newDeaths;

    @Column(
            name = "new_deaths_smoothed"
    )
    private BigDecimal newDeathsSmooth;

    @Column(
            name = "total_cases_per_million"
    )
    private BigDecimal totalCasesPerMillion;

    @Column(
            name = "new_cases_per_million"
    )
    private BigDecimal newCasesPerMillion;

    @Column(
            name = "new_cases_smoothed_per_million"
    )
    private BigDecimal newCasesSmoothedPerMillion;

    @Column(
            name = "total_deaths_per_million"
    )
    private BigDecimal totalDeathsPerMillion;

    @Column(
            name = "new_deaths_per_million"
    )
    private BigDecimal newDeathsPerMillion;

    @Column(
            name = "new_deaths_smoothed_per_million"
    )
    private BigDecimal newDeathsSmoothedPerMillion;

    @Column(
            name = "reproduction_rate"
    )
    private BigDecimal reproductionRate;

}
