package com.example.email_service.service;


import com.example.email_service.data.EmailDetails;
import org.springframework.stereotype.Service;



@Service
public class RecipeService {

    private final EmailService emailService;


    public RecipeService(EmailService emailService) {
        this.emailService = emailService;
    }


    public void acceptRecipe(Long recipeId, String admin, String email) {

        emailService.sendSimpleMail(
                new EmailDetails(email,
                        "Ваш рецепт №" + recipeId + " был проверен и добавлен администратором " + admin,
                        "Добавление рецепта на сайте povarenok.ru"
                )
        );


    }


    public void declineRecipe(Long recipeId, String admin, String declineReason,
                              String email) {

        emailService.sendSimpleMail(
                new EmailDetails(email,
                        "Ваш рецепт №" + recipeId + " был отклонен администратором " + admin + " по причине: " + declineReason,
                        "Добавление рецепта на сайте povarenok.ru"
                )
        );
    }
}


