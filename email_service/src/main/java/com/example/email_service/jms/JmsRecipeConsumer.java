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
        Long recipeId = message.getLongProperty("recipeId");
        String admin = message.getStringProperty("admin");
        System.out.println(recipeId);
        System.out.println(admin);
        recipeService.acceptRecipe(recipeId, admin);
    }


    @JmsListener(destination = "${recipe.decline.queue}")
    public void declineRecipe(Message message) throws JMSException {
        Long recipeId = message.getLongProperty("recipeId");
        String admin = message.getStringProperty("admin");
        String declineReason = message.getStringProperty("declineReason");
        System.out.println(recipeId);
        System.out.println(admin);
        System.out.println(declineReason);
        recipeService.declineRecipe(recipeId, admin, declineReason);
    }
}
