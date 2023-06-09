package com.example.data.dto.response;


import com.example.data.model.basic.Dish;
import com.example.data.model.basic.Ingredients;
import com.example.data.model.basic.NationalCuisine;
import com.example.data.model.basic.Tastes;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class RecipeResponse {

    private Long recipeNumber;
    private String description;
    private Integer countPortion;
    private String userLogin;
    private NationalCuisine nationalCuisine;
    private Dish dish;
    private List<Tastes> tastes;
    private List<Ingredients> ingredients;

    public RecipeResponse(Long recipeNumber, String description, Integer countPortion,
                          String userLogin, NationalCuisine nationalCuisine, Dish dish, List<Tastes> tastes, List<Ingredients> ingredients) {
        this.recipeNumber = recipeNumber;
        this.description = description;
        this.countPortion = countPortion;
        this.userLogin = userLogin;
        this.nationalCuisine = nationalCuisine;
        this.dish = dish;
        this.tastes = tastes;
        this.ingredients = ingredients;
    }

}
