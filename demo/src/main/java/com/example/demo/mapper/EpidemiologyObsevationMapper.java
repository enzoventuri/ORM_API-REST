package com.example.demo.mapper;

import com.example.demo.dto.EpidemiologyObservationResponse;
import com.example.demo.entity.observation.EpidemiologyObservation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class EpidemiologyObsevationMapper {
    public EpidemiologyObservationResponse toResponse(EpidemiologyObservation entity) {
        return new EpidemiologyObservationResponse(
                entity.getId(),
                entity.getTotalCases(),
                entity.getNewCases(),
                entity.getNewCasesSmooth(),
                entity.getNewDeaths(),
                entity.getNewDeathsSmooth(),
                entity.getTotalCasesPerMillion(),
                entity.getNewCasesPerMillion(),
                entity.getNewCasesSmoothedPerMillion(),
                entity.getTotalDeathsPerMillion(),
                entity.getNewDeathsPerMillion(),
                entity.getNewDeathsSmoothedPerMillion(),
                entity.getReproductionRate()
        );
    }

    public Page<EpidemiologyObservationResponse> toResponsePage(Page<EpidemiologyObservation> entities) {
        return entities.map(this::toResponse);
    }

}
