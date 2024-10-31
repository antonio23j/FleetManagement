package com.example.fleetmanagement.owner.controllers;


import com.example.fleetmanagement.owner.dto.OwnerDTO;
import com.example.fleetmanagement.shared.models.QueryResponse;
import com.example.fleetmanagement.shared.models.SimpleMessage;
import com.example.fleetmanagement.owner.models.Owner;
import com.example.fleetmanagement.owner.services.OwnerService;
import jakarta.websocket.OnClose;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.querydsl.core.types.Predicate;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<SimpleMessage> createOwner(@RequestBody Owner owner){
        return ownerService.createOwner(owner);

    }

    @PutMapping
    public ResponseEntity<SimpleMessage> updateOwner(@RequestBody Owner owner){
        return ownerService.updateOwner(owner);

    }

    @DeleteMapping
    public ResponseEntity<SimpleMessage> deleteOwner(@RequestParam String id){
        return ownerService.deleteOwner(id);

    }

    @GetMapping
    public QueryResponse<Owner> getOwners(@QuerydslPredicate Predicate predicate, Pageable pageable){
        return ownerService.getOwners(predicate,pageable);
    }

    //DTO methods

    @GetMapping("/dto/getOwnerDto")
    public List<OwnerDTO> getOwnerDto(){
        return ownerService.getOwnerDto();
    }

    @GetMapping("/dto/getOwnerDtoById")
    public OwnerDTO getOwnerDtoById(@RequestParam String id){
        return ownerService.getOwnerDtoById(id);
    }

    @PostMapping("/dto/createOwnerDto")
    public ResponseEntity<SimpleMessage> createOwnerDto(@RequestBody OwnerDTO ownerDTO){
        return  ownerService.createOwnerDto(ownerDTO);
    }
}
