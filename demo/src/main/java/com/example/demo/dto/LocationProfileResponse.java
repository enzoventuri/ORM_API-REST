package com.example.demo.dto;

import com.example.demo.entity.location.Location;

import java.math.BigDecimal;

public record LocationProfileResponse(
        Long locationId,
        Long population,
        BigDecimal populationDensity,
        BigDecimal medianAge,
        BigDecimal aged65AndOlder,
        BigDecimal aged70AndOlder,
        BigDecimal gdpPerCapita,
        BigDecimal extremePoverty,
        BigDecimal cardiovascularDeathRate,
        BigDecimal diabetesPrevalence,
        BigDecimal femaleSmokers,
        BigDecimal maleSmokers,
        BigDecimal handwashingFacilities,
        BigDecimal hospitalBedsPerThousand,
        BigDecimal lifeExpectancy,
        BigDecimal humanDevelopmentIndex
) {
}
