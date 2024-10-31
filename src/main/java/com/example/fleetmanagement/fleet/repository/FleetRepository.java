package com.example.fleetmanagement.fleet.repository;

import com.example.fleetmanagement.fleet.models.*;
import com.example.fleetmanagement.shared.models.FleetAggregationResponse;
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
public interface FleetRepository extends MongoRepository<Fleet, String>, QuerydslPredicateExecutor<Fleet>, QuerydslBinderCustomizer<QFleet> {

    @Override
    default void customize(QuerydslBindings bindings, QFleet root) {

        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

        bindings.bind(root.description).all(((path, value) -> {
            Iterator<? extends String> it = value.iterator();
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            while (it.hasNext()) {
                String n = it.next();
                booleanBuilder.or(root.description.containsIgnoreCase(n));
            }
            return Optional.of(booleanBuilder);
        }));
    }

    String FLEET_GROUP_STAGE = "{'$group' :  {'_id' : '$_id'}}";
    String FLEET_COUNT_STAGE = "{'$count': 'totalElements'}";
    String FLEET_MATCH_STAGE = "{'$match': { 'ownerId' : '?0'} }";


    @Aggregation(pipeline = {FLEET_MATCH_STAGE, FLEET_GROUP_STAGE})
    List<FleetAggregationResponse> findFleetByAggregationMethodV2();

    @Aggregation(pipeline = {FLEET_MATCH_STAGE, FLEET_GROUP_STAGE})
    List<String> getFleetIds(String ownerId);

    Page<Fleet> findAllByOwnerId(Pageable pageable, String ownerId);

    void deleteAllByOwnerId(String id);
}
