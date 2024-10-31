package com.example.fleetmanagement.vehicle.services;

import com.example.fleetmanagement.shared.models.QueryResponse;
import com.example.fleetmanagement.shared.models.SimpleMessage;
import com.example.fleetmanagement.shared.models.VehicleAggregationResponse;
import com.example.fleetmanagement.shared.services.QueryResponseConverter;
import com.example.fleetmanagement.vehicle.dto.VehicleDTO;
import com.example.fleetmanagement.shared.services.VehicleDTOService;
import com.example.fleetmanagement.vehicle.models.Vehicle;
import com.example.fleetmanagement.vehicle.repository.VehicleRepository;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final QueryResponseConverter<Vehicle> queryResponseConverter;

    public ResponseEntity<SimpleMessage> createVehicle(Vehicle vehicle){
        vehicleRepository.insert(vehicle);
        return ResponseEntity.ok(new SimpleMessage("Vehicle created successfully"));
    }

    public ResponseEntity<SimpleMessage> updateVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok(new SimpleMessage("Vehicle updated successfully"));
    }

    public ResponseEntity<SimpleMessage> deleteVehicle(String id){
        vehicleRepository.deleteById(id);
        return ResponseEntity.ok(new SimpleMessage("Vehicle deleted successfully"));
    }

    public QueryResponse<Vehicle> getVehicles(Predicate predicate, Pageable pageable){
        Page<Vehicle> page = vehicleRepository.findAll(predicate,pageable);
        return queryResponseConverter.convert(page);
    }

    public QueryResponse<Vehicle> getVehiclesByFleetId(String id, Pageable pageable) {
        Page<Vehicle> page1 = vehicleRepository.findAllByFleetId(pageable, id);
        return queryResponseConverter.convert(page1);
    }

    public VehicleAggregationResponse getAllCars(String make){
        return vehicleRepository.getAllCars(make);
    }

    //DTO methods

    public List<VehicleDTO> getVehicleDto(){
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(VehicleDTOService::mapToVehicleDto)
                .collect(Collectors.toList());
    }

    public VehicleDTO getVehicleDtoById(String id){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        Vehicle vehicle = optionalVehicle.get();
        return VehicleDTOService.mapToVehicleDto(vehicle);
    }

    public ResponseEntity<SimpleMessage> createVehicleDto(VehicleDTO vehicleDto){
        vehicleRepository.save(VehicleDTOService.mapToVehicle(vehicleDto));
        return ResponseEntity.ok(new SimpleMessage("Vehicle DTO created successfully"));
    }
}
