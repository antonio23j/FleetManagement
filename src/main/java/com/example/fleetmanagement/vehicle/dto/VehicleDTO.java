package com.example.fleetmanagement.vehicle.dto;

import com.example.fleetmanagement.vehicle.models.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleDTO {

    private String make;
    private String model;

    public VehicleDTO(Vehicle vehicle){
        this.make = vehicle.getMake();
        this.model = vehicle.getModel();
    }
}
