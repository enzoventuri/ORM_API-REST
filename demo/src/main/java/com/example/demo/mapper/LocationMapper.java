package com.example.demo.mapper;

import com.example.demo.dto.LocationResponse;
import com.example.demo.entity.location.Location;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public LocationResponse toResponse(Location location) {
        return new LocationResponse(
                location.getId(),
                location.getIsoCode(),
                location.getName(),
                location.getContinent(),
                location.getLocationType()
        );
    }

    public Page<LocationResponse> toResponsePage(Page<Location> locations) {
        return locations.map(this::toResponse);
    }
}
