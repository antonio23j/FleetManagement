package com.example.fleetmanagement.owner.services;


import com.example.fleetmanagement.fleet.repository.FleetRepository;
import com.example.fleetmanagement.owner.dto.OwnerDTO;
import com.example.fleetmanagement.shared.services.OwnerDTOService;
import com.example.fleetmanagement.shared.models.QueryResponse;
import com.example.fleetmanagement.shared.models.SimpleMessage;
import com.example.fleetmanagement.owner.models.Owner;
import com.example.fleetmanagement.owner.repository.OwnerRepository;
import com.example.fleetmanagement.shared.services.QueryResponseConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final FleetRepository fleetRepository;
    private final QueryResponseConverter<Owner> queryResponseConverter;

    public ResponseEntity<SimpleMessage> createOwner(Owner owner){
        ownerRepository.insert(owner);
        return ResponseEntity.ok(new SimpleMessage("Owner created successfully"));
    }

    public ResponseEntity<SimpleMessage> updateOwner(Owner owner){
        ownerRepository.save(owner);
        return ResponseEntity.ok(new SimpleMessage("Owner updated successfully"));
    }

    public ResponseEntity<SimpleMessage> deleteOwner(String id){
        ownerRepository.deleteById(id);
        fleetRepository.deleteAllByOwnerId(id);
        return ResponseEntity.ok(new SimpleMessage("Owner deleted successfully"));
    }

    public QueryResponse<Owner> getOwners(Predicate predicate, Pageable pageable){
        Page<Owner> page = ownerRepository.findAll(predicate, pageable);
        return queryResponseConverter.convert(page);
    }

    //DTO methods

    public List<OwnerDTO> getOwnerDto(){
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream()
                .map(OwnerDTOService::mapToOwnerDto)
                .collect(Collectors.toList());
    }

    public OwnerDTO getOwnerDtoById(String id){
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        Owner owner = optionalOwner.get();
        return OwnerDTOService.mapToOwnerDto(owner);
    }

    public ResponseEntity<SimpleMessage> createOwnerDto(OwnerDTO ownerDTO){
        ownerRepository.save(OwnerDTOService.mapToOwner(ownerDTO));
        return ResponseEntity.ok(new SimpleMessage("Owner DTO created successfully"));
    }

}
