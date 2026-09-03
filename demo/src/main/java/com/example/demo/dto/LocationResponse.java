package com.example.demo.dto;

import com.example.demo.entity.continent.Continent;
import com.example.demo.entity.location.LocationType;

public record LocationResponse(
        Long id,
        String isoCode,
        String name,
        Continent continent,
        LocationType locationType
) {
}
