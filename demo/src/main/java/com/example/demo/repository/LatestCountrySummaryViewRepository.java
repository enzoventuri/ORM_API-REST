package com.example.demo.repository;

import com.example.demo.dto.LatestCountrySummaryViewResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class LatestCountrySummaryViewRepository {
    private EntityManager entityManager;

    @Autowired
    public LatestCountrySummaryViewRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<LatestCountrySummaryViewResponse> findLatestCountrySummary() {
        String sql = """
                SELECT 
                    iso_code, location, continent, observation_date,
                    population, total_cases, total_deaths, case_fatality_percentage,
                    total_cases_per_million, total_deaths_per_million
                FROM covid.vw_latest_country_summary
                """;

        List<Object[]> results = entityManager
                .createNativeQuery(sql)
                .getResultList();

        return results.stream()
                .map(r -> new LatestCountrySummaryViewResponse(
                        ((String) r[0]),
                        ((String) r[1]),
                        ((String) r[2]),
                        ((LocalDate) r[3]),
                        ((Long) r[4]),
                        ((BigDecimal) r[5]),
                        ((BigDecimal) r[6]),
                        ((BigDecimal) r[7]),
                        ((BigDecimal) r[8]),
                        ((BigDecimal) r[9])
                ))
                .toList();
    }

}
