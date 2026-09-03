package com.example.demo.repository;

import com.example.demo.entity.location.LocationProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationProfileRepository extends JpaRepository<LocationProfile, Long> {
    Page<LocationProfile> findByLocationIsoCode(
            String isoCode,
            Pageable pageable
    );
}
