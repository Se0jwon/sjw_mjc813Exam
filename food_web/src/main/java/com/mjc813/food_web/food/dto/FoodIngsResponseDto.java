package com.mjc813.food_web.food.dto;

import com.mjc813.food_web.food_file.dto.FoodFileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FoodIngsResponseDto extends FoodIngsRequestDto {
    private List<FoodFileDto> fileList;
}
