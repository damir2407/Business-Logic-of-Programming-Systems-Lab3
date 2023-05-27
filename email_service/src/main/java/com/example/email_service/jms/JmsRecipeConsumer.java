package com.example.email_service.jms;

import com.example.data.model.basic.RecipeOnReview;
import com.example.email_service.service.RecipeService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;

@Controller
public class JmsRecipeConsumer {

    private final RecipeService recipeService;

    public JmsRecipeConsumer(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @JmsListener(destination = "${recipe.accept.queue}")
    public void acceptRecipe(RecipeOnReview recipeOnReview, String admin) {
        recipeService.acceptRecipe(recipeOnReview, admin);
    }

    @JmsListener(destination = "${recipe.decline.queue}")
    public void declineRecipe(Long id, String declineReason, String admin) {
        recipeService.declineRecipe(id, declineReason, admin);
    }
}
