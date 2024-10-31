package com.example.fleetmanagement.owner.models;

import com.example.fleetmanagement.owner.dto.OwnerDTO;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@QueryEntity
public class Owner {

    @Id
    private String id;
    private String name;
    private String lastName;
    private Address address;

    public Owner(OwnerDTO ownerDTO){
        this.name = ownerDTO.getName();
        this.lastName = ownerDTO.getLastName();
    }


}



