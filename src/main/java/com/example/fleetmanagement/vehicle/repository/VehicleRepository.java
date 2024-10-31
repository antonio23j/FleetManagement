package com.example.fleetmanagement.vehicle.repository;

import com.example.fleetmanagement.dashboard.model.VehicleAggregation;
import com.example.fleetmanagement.shared.models.VehicleAggregationResponse;
import com.example.fleetmanagement.vehicle.models.QVehicle;
import com.example.fleetmanagement.vehicle.models.Vehicle;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String>, QuerydslPredicateExecutor<Vehicle>, QuerydslBinderCustomizer<QVehicle> {

    @Override
    default void customize(QuerydslBindings bindings, QVehicle root){


        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

        bindings.bind(root.make).all(((path, value) -> {
            Iterator<? extends String> it = value.iterator();
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            while (it.hasNext()) {
                String n = it.next();
                booleanBuilder.or(root.make.containsIgnoreCase(n));
            }
            return Optional.of(booleanBuilder);
        }));


    };


    String VEHICLE_MATCH_STAGE = "{'$match' : {'fleetId' : { '$in' : ?0 } } }";
    String VEHICLE_COUNT_STAGE = "{'$count' : 'totalCars'}";
    String VEHICLE_GROUP_STAGE = "{'$group' : { '_id' : '$fleetId', 'total' : { '$sum': 1 } } }";

    @Aggregation(pipeline = {VEHICLE_MATCH_STAGE, VEHICLE_COUNT_STAGE})
    VehicleAggregationResponse getAllCars(String make);

    @Aggregation(pipeline = {VEHICLE_MATCH_STAGE, VEHICLE_GROUP_STAGE})
    List<VehicleAggregation> getVehiclesOfFleets(List<String> fleetIds);

    Page<Vehicle> findAllByFleetId(Pageable pageable, String id);

    void deleteAllByFleetId(String id);
}
