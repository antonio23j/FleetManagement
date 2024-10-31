package com.example.fleetmanagement.fleet.models;


import com.example.fleetmanagement.fleet.dto.FleetDTO;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@QueryEntity
public class Fleet {

    @Id
    private String id;
    private String name;
    private String description;
    private String ownerId;
    private int vehicleNumber;

    public Fleet(FleetDTO fleetDTO){
        this.name = fleetDTO.getName();
        this.description = fleetDTO.getDescription();
    }

}
