package com.example.fleetmanagement.owner.repository;


import com.example.fleetmanagement.owner.models.Owner;
import com.example.fleetmanagement.owner.models.QOwner;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.Optional;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String>, QuerydslPredicateExecutor<Owner>, QuerydslBinderCustomizer<QOwner> {

    @Override
    default void customize(QuerydslBindings bindings, QOwner root){

        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);


        bindings.bind(root.name).all(((path, value) -> {
            Iterator<? extends String> it = value.iterator();
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            while (it.hasNext()) {
                String n = it.next();
                booleanBuilder.or(root.name.containsIgnoreCase(n));
            }
            return Optional.of(booleanBuilder);
        }));


    }

}
