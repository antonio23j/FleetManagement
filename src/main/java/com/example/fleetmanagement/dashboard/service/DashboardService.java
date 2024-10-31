package com.example.fleetmanagement.dashboard.service;


import com.example.fleetmanagement.dashboard.model.VehicleAggregation;
import com.example.fleetmanagement.fleet.repository.FleetRepository;
import com.example.fleetmanagement.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {


    private final FleetRepository fleetRepository;
    private final VehicleRepository vehicleRepository;

    private List<String> getFleetIds(String ownerId){
        return fleetRepository.getFleetIds(ownerId);
    }

    public List<VehicleAggregation> getVehiclesOfFleets(String ownerId){
        List<String> ids = getFleetIds(ownerId);
        return vehicleRepository.getVehiclesOfFleets(ids);
    }


}
