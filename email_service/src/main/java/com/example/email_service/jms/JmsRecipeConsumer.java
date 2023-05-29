package com.example.email_service.jms;


import com.example.email_service.service.RecipeService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;

import javax.jms.JMSException;
import javax.jms.Message;

@Controller
public class JmsRecipeConsumer {


    private final RecipeService recipeService;

    public JmsRecipeConsumer(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @JmsListener(destination = "${recipe.accept.queue}")
    public void acceptRecipe(Message message) throws JMSException {
        System.out.println(message.getStringProperty("recipeId"));
        System.out.println(message.getStringProperty("admin"));
//            recipeService.acceptRecipe(recipeOnReview, admin);

    }


    @JmsListener(destination = "${recipe.decline.queue}")
    public void declineRecipe(Message message) throws JMSException {
        System.out.println(message.getStringProperty("recipeId"));
        System.out.println(message.getStringProperty("admin"));
        System.out.println(message.getStringProperty("declineReason"));
//        recipeService.declineRecipe(id, declineReason, admin);
    }
}
