package com.example.fleetmanagement.owner.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address{
    private String state;
    private String city;
    private int postalCode;
}
