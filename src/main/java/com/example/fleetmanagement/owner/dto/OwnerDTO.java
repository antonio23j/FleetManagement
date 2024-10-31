package com.example.fleetmanagement.owner.dto;


import com.example.fleetmanagement.owner.models.Owner;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerDTO {

    private String name;
    private String lastName;

    public OwnerDTO(Owner owner){
        this.name = owner.getName();
        this.lastName = owner.getLastName();
    }
}
