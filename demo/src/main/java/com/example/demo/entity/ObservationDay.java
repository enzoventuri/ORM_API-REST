package com.example.demo.entity;

import com.example.demo.composite_ids.ObservationDayId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "observation_day")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ObservationDay {
    @EmbeddedId
    private ObservationDayId observationDayId;
}
