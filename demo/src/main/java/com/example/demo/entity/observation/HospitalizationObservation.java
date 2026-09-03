package com.example.demo.entity.observation;

import com.example.demo.composite_ids.ObservationDayId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(schema = "covid", name = "hospitalization_observation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HospitalizationObservation {
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

    @Column(name = "icu_patients")
    private BigDecimal icuPatients;

    @Column(name = "icu_patients_per_million")
    private BigDecimal icuPatientsPerMillion;

    @Column(name = "hosp_patients")
    private BigDecimal hospitalPatients;

    @Column(name = "hosp_patients_per_million")
    private BigDecimal hospitalPatientsPerMillion;

    @Column(name = "weekly_icu_admissions")
    private BigDecimal weeklyIcuAdmissions;

    @Column(name = "weekly_icu_admissions_per_million")
    private BigDecimal weeklyIcuAdmissionsPerMillion;

    @Column(name = "weekly_hosp_admissions")
    private BigDecimal weeklyHospitalAdmissions;

    @Column(name = "weekly_hosp_admissions_per_million")
    private BigDecimal weeklyHospitalAdmissionsPerMillion;

}
