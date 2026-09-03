package com.example.demo.entity.load;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "covid", name = "etl_load")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EtlLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "load_id",
            nullable = false
    )
    private Long loadId;

    @Column(
            name = "source_filename",
            nullable = false,
            length = 255
    )
    private String sourceFilename;

    @Column(
            name = "raw_row_count",
            nullable = false
    )
    private Long rawRowCount;

    @Column(
            name = "normalized_day_count",
            nullable = false
    )
    private Long normalizedDayCount;

    @Column(
            name = "complementary_duplicate_count",
            nullable = false
    )
    private Long complementaryDuplicateCount;

    @Builder.Default
    @Column(
            name = "loaded_at",
            nullable = false
    )
    private LocalDateTime loadedAt = LocalDateTime.now();
}
