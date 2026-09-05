package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LatestCountrySummaryViewResponse(
        String isoCode,
        String location,
        String continent,
        LocalDate observationDate,
        Long population,
        BigDecimal totalCases,
        BigDecimal totalDeaths,
        BigDecimal caseFatalityPercentage,
        BigDecimal totalCasesPerMillion,
        BigDecimal totalDeathsPerMillion
) {
}
