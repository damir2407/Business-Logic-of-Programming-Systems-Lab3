package com.example.data.dto;


import com.example.data.dto.response.RecipeResponse;
import com.example.data.model.basic.Recipe;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RecipeDTOMapper implements Function<Recipe, RecipeResponse>{
    @Override
    public RecipeResponse apply(Recipe recipe) {
        return new RecipeResponse(
                recipe.getId(),
                recipe.getDescription(),
                recipe.getCountPortion(),
                recipe.getUser().getLogin(),
                recipe.getNationalCuisine(),
                recipe.getDish(),
                recipe.getTastes(),
                recipe.getIngredients()
        );
    }
}
