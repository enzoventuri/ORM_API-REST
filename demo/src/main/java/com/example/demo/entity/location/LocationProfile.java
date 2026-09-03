package com.example.demo.entity.location;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(schema = "covid", name = "location_profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LocationProfile {
    @Id
    @Column(
            name = "location_id"
    )
    private Long locationId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(
            name = "population",
            nullable = false
    )
    private Long population;

    @Column(
            name = "population_density"
    )
    private BigDecimal populationDensity;

    @Column(
            name = "median_age"
    )
    private BigDecimal medianAge;

    @Column(
            name = "aged_65_older"
    )
    private BigDecimal aged65AndOlder;

    @Column(
            name = "aged_70_older"
    )
    private BigDecimal aged70AndOlder;

    @Column(
            name = "gdp_per_capita"
    )
    private BigDecimal gdpPerCapita;

    @Column(
            name = "extreme_poverty"
    )
    private BigDecimal extremePoverty;

    @Column(
            name = "cardiovasc_death_rate"
    )
    private BigDecimal cardiovascularDeathRate;

    @Column(
            name = "diabetes_prevalence"
    )
    private BigDecimal diabetesPrevalence;

    @Column(
            name = "female_smokers"
    )
    private BigDecimal femaleSmokers;

    @Column(
            name = "male_smokers"
    )
    private BigDecimal maleSmokers;

    @Column(
            name = "handwashing_facilities"
    )
    private BigDecimal handwashingFacilities;

    @Column(
            name = "hospital_beds_per_thousand"
    )
    private BigDecimal hospitalBedsPerThousand;

    @Column(
            name = "life_expectancy"
    )
    private BigDecimal lifeExpectancy;

    @Column(
            name = "human_development_index"
    )
    private BigDecimal humanDevelopmentIndex;

}
