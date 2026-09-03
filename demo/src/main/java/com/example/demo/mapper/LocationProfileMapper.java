package com.example.demo.mapper;

import com.example.demo.dto.LocationProfileResponse;
import com.example.demo.entity.location.LocationProfile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationProfileMapper {
    public LocationProfileResponse toLocationProfileResponse(LocationProfile locationProfile) {
        return new LocationProfileResponse(
                locationProfile.getLocationId(),
                locationProfile.getPopulation(),
                locationProfile.getPopulationDensity(),
                locationProfile.getMedianAge(),
                locationProfile.getAged65AndOlder(),
                locationProfile.getAged70AndOlder(),
                locationProfile.getGdpPerCapita(),
                locationProfile.getExtremePoverty(),
                locationProfile.getCardiovascularDeathRate(),
                locationProfile.getDiabetesPrevalence(),
                locationProfile.getFemaleSmokers(),
                locationProfile.getMaleSmokers(),
                locationProfile.getHandwashingFacilities(),
                locationProfile.getHospitalBedsPerThousand(),
                locationProfile.getLifeExpectancy(),
                locationProfile.getHumanDevelopmentIndex()
        );
    }

    public Page<LocationProfileResponse> toLocationProfilePageResponse(Page<LocationProfile> locationProfileList) {
        return locationProfileList.map(this::toLocationProfileResponse);
    }

}
