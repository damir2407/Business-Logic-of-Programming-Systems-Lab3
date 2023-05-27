package com.example.data.mapper;

import com.example.data.model.basic.*;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RecipeOnReviewToRecipeMapper implements Function<RecipeOnReview, Recipe> {

    @Override
    public Recipe apply(RecipeOnReview recipeOnReview) {
        return new Recipe(recipeOnReview.getId(),recipeOnReview.getDescription(),
                recipeOnReview.getCountPortion(),
                recipeOnReview.getUser(),
                recipeOnReview.getNationalCuisine(),
                recipeOnReview.getDish(),
                recipeOnReview.getTastes(),
                recipeOnReview.getIngredients());
    }


}
