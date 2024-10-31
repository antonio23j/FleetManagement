package com.example.fleetmanagement.dashboard.controller;


import com.example.fleetmanagement.dashboard.model.VehicleAggregation;
import com.example.fleetmanagement.dashboard.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class DashboardController {

    DashboardService dashboardService;

    @GetMapping
    public List<VehicleAggregation> getVehiclesOfFleets(@RequestParam String ownerId){
        return dashboardService.getVehiclesOfFleets(ownerId);
    }
}
