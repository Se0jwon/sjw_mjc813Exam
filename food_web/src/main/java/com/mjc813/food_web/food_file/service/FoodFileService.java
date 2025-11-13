package com.mjc813.food_web.food_file.service;

import com.mjc813.food_web.food.dto.FoodEntity;
import com.mjc813.food_web.food_file.dto.FoodFileDto;
import com.mjc813.food_web.food_file.dto.FoodFileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodFileService {
    @Autowired
    private FoodFileRepository foodFileRepository;

    public void insert(FoodFileDto foodFileDto) {
        FoodFileEntity foodFileEntity = new FoodFileEntity();
        foodFileEntity.copyMembersFoodFile(foodFileDto);
        this.foodFileRepository.save(foodFileEntity);
    }

    public void update(FoodFileDto foodFileDto) {
        FoodFileEntity foodFileEntity = new FoodFileEntity();
        foodFileEntity.copyMembersNotNullFoodFile(foodFileDto);
        this.foodFileRepository.save(foodFileEntity);
    }

    public List<FoodFileEntity> findByFoodId(Long foodId) {
        FoodEntity param = FoodEntity.builder().id(foodId).build();
        List<FoodFileEntity> findList = this.foodFileRepository.findByFoodEntity(param);
        return findList;
    }

    public FoodFileEntity findById(Long id) {
        Optional<FoodFileEntity> find = this.foodFileRepository.findById(id);
        if ( find.isPresent() ) {
            return find.get();
        }
        return null;
    }
}
