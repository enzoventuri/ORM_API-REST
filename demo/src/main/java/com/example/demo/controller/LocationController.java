package com.example.demo.controller;

import com.example.demo.dto.LocationProfileResponse;
import com.example.demo.dto.LocationResponse;
import com.example.demo.entity.location.Location;
import com.example.demo.entity.location.LocationProfile;
import com.example.demo.mapper.LocationMapper;
import com.example.demo.mapper.LocationProfileMapper;
import com.example.demo.repository.LocationProfileRepository;
import com.example.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {
    private final LocationRepository locationRepository;
    private final LocationProfileRepository locationProfileRepository;

    private final LocationMapper locationMapper;
    private final LocationProfileMapper locationProfileMapper;

    @Autowired
    public LocationController(LocationRepository locationRepository,
                              LocationProfileRepository locationProfileRepository,
                              LocationMapper locationMapper,
                              LocationProfileMapper locationProfileMapper) {
        this.locationRepository = locationRepository;
        this.locationProfileRepository = locationProfileRepository;
        this.locationMapper = locationMapper;
        this.locationProfileMapper = locationProfileMapper;
    }

    @GetMapping("/")
    public ResponseEntity<Page<LocationResponse>> getAllLocations(Pageable page) {
        Page<Location> locations = locationRepository.findAll(page);

        return ResponseEntity.ok(locationMapper.toResponsePage(locations));
    }

    @GetMapping("{isoCode}")
    public ResponseEntity<Page<LocationResponse>> getAllLocationsByIsoCode(@PathVariable String isoCode, Pageable pageable) {
        Page<Location> locations = locationRepository.findByIsoCode(isoCode, pageable);

        return ResponseEntity.ok(locationMapper.toResponsePage(locations));
    }

    @GetMapping("{isoCode}/profile")
    public ResponseEntity<Page<LocationProfileResponse>> getAllProfilesFromIsoCode(@PathVariable String isoCode,
                                                                                   Pageable pageable) {
        Page<LocationProfile> locationProfiles = locationProfileRepository.findByLocationIsoCode(
                isoCode, pageable
        );

        return ResponseEntity.ok(locationProfileMapper.toLocationProfilePageResponse(locationProfiles));
    }

    @GetMapping("{isoCode}/epidemiology")
    public ResponseEntity<Page<LocationProfileResponse>> getAllEpidemiologyObservationsFromIsoCode(@PathVariable String isoCode,
                                                                                                   Pageable pageable,
                                                                                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                                                    LocalDateTime startTime,
                                                                                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                                                       LocalDateTime endTime
                                                                                                   ) {
        Page<LocationProfile> locationProfiles = locationProfileRepository.findByLocationIsoCode(
                isoCode, pageable
        );

        return ResponseEntity.ok(locationProfileMapper.toLocationProfilePageResponse(locationProfiles));
    }

}

