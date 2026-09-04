package com.example.demo.dto;

import com.example.demo.composite_ids.ObservationDayId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;

public record EpidemiologyObservationResponse(
        ObservationDayId id,
        BigDecimal totalCases,
        BigDecimal newCases,
        BigDecimal newCasesSmooth,
        BigDecimal newDeaths,
        BigDecimal newDeathsSmooth,
        BigDecimal totalCasesPerMillion,
        BigDecimal newCasesPerMillion,
        BigDecimal newCasesSmoothedPerMillion,
        BigDecimal totalDeathsPerMillion,
        BigDecimal newDeathsPerMillion,
        BigDecimal newDeathsSmoothedPerMillion,
        BigDecimal reproductionRate
) {
}
