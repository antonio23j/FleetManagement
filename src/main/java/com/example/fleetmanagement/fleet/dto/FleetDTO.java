package com.example.fleetmanagement.fleet.dto;


import com.example.fleetmanagement.fleet.models.Fleet;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FleetDTO {
    private String name;
    private String description;

    public FleetDTO(Fleet fleet){
        this.name = fleet.getName();
        this.description = fleet.getDescription();
    }
}
