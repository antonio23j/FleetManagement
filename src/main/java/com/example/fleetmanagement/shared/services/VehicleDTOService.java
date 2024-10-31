package com.example.fleetmanagement.shared.services;

import com.example.fleetmanagement.vehicle.dto.VehicleDTO;
import com.example.fleetmanagement.vehicle.models.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleDTOService {

    public static VehicleDTO mapToVehicleDto(Vehicle vehicle){
        return new VehicleDTO(vehicle);
    }

    public static Vehicle mapToVehicle(VehicleDTO vehicleDTO){
        return new Vehicle(vehicleDTO);
    }
}
