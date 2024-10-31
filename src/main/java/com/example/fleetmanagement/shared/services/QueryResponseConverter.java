package com.example.fleetmanagement.shared.services;

import com.example.fleetmanagement.shared.models.QueryResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class QueryResponseConverter<E> {

    public QueryResponse<E> convert(Page<E> page){
        return new QueryResponse<E>(page.getTotalElements(), page.getContent());
    }
}
