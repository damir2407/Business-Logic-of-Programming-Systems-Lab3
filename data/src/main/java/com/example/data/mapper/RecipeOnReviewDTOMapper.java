package com.example.data.mapper;


import com.example.data.dto.response.RecipeResponse;
import com.example.data.model.basic.RecipeOnReview;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class RecipeOnReviewDTOMapper implements Function<RecipeOnReview, RecipeResponse>{


    @Override
    public RecipeResponse apply(RecipeOnReview recipeOnReview) {
        return new RecipeResponse(
                recipeOnReview.getId(),
                recipeOnReview.getDescription(),
                recipeOnReview.getCountPortion(),
                recipeOnReview.getUser().getLogin(),
                recipeOnReview.getNationalCuisine(),
                recipeOnReview.getDish(),
                recipeOnReview.getTastes(),
                recipeOnReview.getIngredients()
        );
    }
}
