package com.example.fleetmanagement.fleet.controllers;

import com.example.fleetmanagement.fleet.dto.FleetDTO;
import com.example.fleetmanagement.fleet.models.Fleet;
import com.example.fleetmanagement.fleet.services.FleetService;
import com.example.fleetmanagement.shared.models.FleetAggregationResponse;
import com.example.fleetmanagement.shared.models.QueryResponse;
import com.example.fleetmanagement.shared.models.SimpleMessage;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/fleet")
public class FleetController {

    private final FleetService fleetService;

    @PostMapping
    public ResponseEntity<SimpleMessage> createFleet(@RequestBody Fleet fleet){
        return fleetService.createFleet(fleet);
    }

    @PutMapping
    public ResponseEntity<SimpleMessage> updateFleet(@RequestBody Fleet fleet){
        return fleetService.updateFleet(fleet);

    }

    @DeleteMapping
    public ResponseEntity<SimpleMessage> deleteFleetById(@RequestParam String id){
        return fleetService.deleteFleet(id);

    }

    @GetMapping
    public QueryResponse<Fleet> getFleets(@QuerydslPredicate(root = Fleet.class) Predicate predicate, Pageable pageable){
        return fleetService.getFleets(predicate, pageable);
    }

    @GetMapping("/byOwnerId")
    public QueryResponse<Fleet> getFleetsByOwnerId(@RequestParam String id, Pageable page){
        return fleetService.getFleetsByOwnerId(id, page);
    }

    @GetMapping("/aggregationV2")
    public List<FleetAggregationResponse> getFleetsAggregationV2(){
        return fleetService.getFleetsAggregationV2();
    }

    //DTO methods

    @GetMapping("/dto/getFleetDto")
    public List<FleetDTO> getFleetDto(){
        return fleetService.getFleetDto();
    }

    @GetMapping("/dto/getFleetDtoById")
    public FleetDTO getFleetDtoById(@RequestParam String id){
        return fleetService.getFleetDtoById(id);
    }

    @PostMapping("/dto/createFleetDto")
    public ResponseEntity<SimpleMessage> createFleetDto(@RequestBody FleetDTO fleetDTO){
        return fleetService.createFleetDto(fleetDTO);
    }
}
