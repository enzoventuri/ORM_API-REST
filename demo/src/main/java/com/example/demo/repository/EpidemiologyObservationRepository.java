package com.example.demo.repository;

import com.example.demo.composite_ids.ObservationDayId;
import com.example.demo.entity.observation.EpidemiologyObservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EpidemiologyObservationRepository extends JpaRepository<EpidemiologyObservation, ObservationDayId> {
    @Query("""
        SELECT eo
        FROM EpidemiologyObservation eo
        JOIN eo.observationDay ob
        JOIN Location loc
            ON ob.observationDayId.idLocation = loc.id
        WHERE loc.isoCode = :isocode
        AND eo.observationDay.observationDayId.observationDate 
        BETWEEN :startDate AND :endDate
""")
    Page<EpidemiologyObservation> findByIsoCodeAndTime(
            @Param("isocode") String isocode,
            @Param("startDate") LocalDate startTime,
            @Param("endDate") LocalDate endTime,
            Pageable pageable
            );

}
