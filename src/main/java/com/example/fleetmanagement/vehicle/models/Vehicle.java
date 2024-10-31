package com.example.fleetmanagement.vehicle.models;


import com.example.fleetmanagement.vehicle.dto.VehicleDTO;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@QueryEntity
public class Vehicle {

    @Id
    private String id;
    private String make;
    private String model;
    private String plate;
    private String fleetId;

    public Vehicle(VehicleDTO vehicleDTO){
        this.make = vehicleDTO.getMake();
        this.model = vehicleDTO.getModel();
    }

}
