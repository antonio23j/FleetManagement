package com.example.fleetmanagement.vehicle.controllers;


import com.example.fleetmanagement.shared.models.QueryResponse;
import com.example.fleetmanagement.shared.models.SimpleMessage;
import com.example.fleetmanagement.shared.models.VehicleAggregationResponse;
import com.example.fleetmanagement.vehicle.dto.VehicleDTO;
import com.example.fleetmanagement.vehicle.models.Vehicle;
import com.example.fleetmanagement.vehicle.services.VehicleService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<SimpleMessage> createVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping
    public ResponseEntity<SimpleMessage> updateVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.updateVehicle(vehicle);
    }

    @DeleteMapping
    public ResponseEntity<SimpleMessage> deleteVehicleById(@RequestParam String id){
        return vehicleService.deleteVehicle(id);
    }

    @GetMapping
    public QueryResponse<Vehicle> getVehicles(@QuerydslPredicate(root = Vehicle.class) Predicate predicate, Pageable pageable){
        return vehicleService.getVehicles(predicate,pageable);
    }

    @GetMapping("/getByFleetId")
    public QueryResponse<Vehicle> getVehiclesByFleetId(@RequestParam String id, Pageable pageable){
        return vehicleService.getVehiclesByFleetId(id, pageable);
    }

    @GetMapping("/aggregation")
    public VehicleAggregationResponse getAllCars(@RequestParam String make){
        return vehicleService.getAllCars(make);
    }

    //DTO methods

    @GetMapping("/dto/getVehicleDto")
    public List<VehicleDTO> getVehicleDto(){
        return vehicleService.getVehicleDto();
    }

    @GetMapping("/dto/getVehicleDtoById")
    public VehicleDTO getVehicleDtoById(@RequestParam String id){
        return vehicleService.getVehicleDtoById(id);
    }

    @PostMapping("/dto/createVehicleDto")
    public ResponseEntity<SimpleMessage> createVehicle(@RequestBody VehicleDTO vehicleDTO){
        return vehicleService.createVehicleDto(vehicleDTO);
    }
}
