package com.example.fleetmanagement.shared.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse <E>{
    private long totalElements;
    private List<E> elements;
}
