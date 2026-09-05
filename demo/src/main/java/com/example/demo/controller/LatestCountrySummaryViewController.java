package com.example.demo.controller;

import com.example.demo.dto.LatestCountrySummaryViewResponse;
import com.example.demo.repository.LatestCountrySummaryViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboards")
public class LatestCountrySummaryViewController {
    private LatestCountrySummaryViewRepository repository;

    @Autowired
    public LatestCountrySummaryViewController(LatestCountrySummaryViewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/latest-summary")
    public List<LatestCountrySummaryViewResponse> viewCountrySummary() {
        return repository.findLatestCountrySummary(); 
    }
}
