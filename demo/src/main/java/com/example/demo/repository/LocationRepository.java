package com.example.demo.repository;

import com.example.demo.entity.location.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Override
    Page<Location> findAll(Pageable pageable);

    Page<Location> findByIsoCode(String isoCode,
                                 Pageable pageable);


}
