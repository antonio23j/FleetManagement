package com.example.fleetmanagement.fleet.services;

import com.example.fleetmanagement.fleet.dto.FleetDTO;
import com.example.fleetmanagement.shared.services.FleetDTOService;
import com.example.fleetmanagement.fleet.models.Fleet;
import com.example.fleetmanagement.fleet.repository.FleetRepository;
import com.example.fleetmanagement.shared.models.FleetAggregationResponse;
import com.example.fleetmanagement.shared.models.QueryResponse;
import com.example.fleetmanagement.shared.models.SimpleMessage;
import com.example.fleetmanagement.shared.services.QueryResponseConverter;
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


@Service
@AllArgsConstructor
public class FleetService {

    private final FleetRepository fleetRepository;
    private final VehicleRepository vehicleRepository;
    private final QueryResponseConverter<Fleet> queryResponseConverter;

    public ResponseEntity<SimpleMessage> createFleet(Fleet fleet){
        fleetRepository.insert(fleet);
        return ResponseEntity.ok(new SimpleMessage("Fleet created successfully"));
    }

    public ResponseEntity<SimpleMessage> updateFleet(Fleet fleet){
        fleetRepository.save(fleet);
        return ResponseEntity.ok(new SimpleMessage("Fleet updated successfully"));
    }

    public ResponseEntity<SimpleMessage> deleteFleet(String id){
        vehicleRepository.deleteAllByFleetId(id);
        fleetRepository.deleteById(id);
        return ResponseEntity.ok(new SimpleMessage("Fleet deleted successfully"));
    }

    public QueryResponse<Fleet> getFleets(Predicate predicate, Pageable pageable){
        Page<Fleet> page= fleetRepository.findAll(predicate, pageable);
        return queryResponseConverter.convert(page);
    }

    public QueryResponse<Fleet> getFleetsByOwnerId(String id, Pageable page){
        Page<Fleet> page1 = fleetRepository.findAllByOwnerId(page, id);
        return queryResponseConverter.convert(page1);
    }

    public List<FleetAggregationResponse> getFleetsAggregationV2(){
        return fleetRepository.findFleetByAggregationMethodV2();
    }

    //DTO methods

    public List<FleetDTO> getFleetDto(){
        List<Fleet> fleets = fleetRepository.findAll();
        return  fleets.stream()
                .map(FleetDTOService::mapToFleetDto)
                .collect(Collectors.toList());
    }

    public FleetDTO getFleetDtoById(String id){
        Optional<Fleet> optionalFleet = fleetRepository.findById(id);
        Fleet fleet = optionalFleet.get();
        return FleetDTOService.mapToFleetDto(fleet);
    }

    public ResponseEntity<SimpleMessage> createFleetDto(FleetDTO fleetDTO){
        fleetRepository.save(FleetDTOService.mapToFleet(fleetDTO));
        return ResponseEntity.ok(new SimpleMessage("Fleet DTO created successfully"));
    }
}
