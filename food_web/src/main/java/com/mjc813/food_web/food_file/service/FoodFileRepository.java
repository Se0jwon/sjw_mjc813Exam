package com.mjc813.food_web.food_file.service;

import com.mjc813.food_web.food.dto.FoodEntity;
import com.mjc813.food_web.food_file.dto.FoodFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodFileRepository extends JpaRepository<FoodFileEntity, Long> {
    List<FoodFileEntity> findByFoodEntity(FoodEntity foodEntity);
}
