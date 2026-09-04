package com.example.demo.composite_ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class    ObservationDayId implements Serializable {
    @Column(name = "location_id")
    private Long idLocation;

    @Column(name = "observation_date")
    private LocalDate observationDate;
}
