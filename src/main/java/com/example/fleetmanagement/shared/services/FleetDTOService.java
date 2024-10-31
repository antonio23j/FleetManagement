package com.example.fleetmanagement.shared.services;

import com.example.fleetmanagement.fleet.dto.FleetDTO;
import com.example.fleetmanagement.fleet.models.Fleet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FleetDTOService {

    public static FleetDTO mapToFleetDto(Fleet fleet){
        return new FleetDTO(fleet);
    }

    public static Fleet mapToFleet(FleetDTO fleetDTO){
        return  new Fleet(fleetDTO);
    }

}
