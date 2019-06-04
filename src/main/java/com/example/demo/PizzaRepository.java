package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PizzaEntity;

@Repository
public interface PizzaRepository extends CrudRepository<PizzaEntity, Long> {

}
