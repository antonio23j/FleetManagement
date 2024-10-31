package com.example.fleetmanagement.shared.services;

import com.example.fleetmanagement.owner.dto.OwnerDTO;
import com.example.fleetmanagement.owner.models.Owner;
import org.springframework.stereotype.Service;

@Service
public class OwnerDTOService {

    public static OwnerDTO mapToOwnerDto(Owner owner) {
        return new OwnerDTO(owner);
    }

    public static Owner mapToOwner(OwnerDTO ownerDTO){
        return  new Owner(ownerDTO);
    }

}
