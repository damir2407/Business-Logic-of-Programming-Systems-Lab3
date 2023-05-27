package com.example.email_service.service;


import com.example.data.mapper.RecipeOnReviewToRecipeMapper;
import com.example.data.model.basic.*;
import com.example.data.repository.basic.RecipeOnReviewRepository;
import com.example.data.repository.basic.RecipeRepository;
import com.example.email_service.data.EmailDetails;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeOnReviewToRecipeMapper recipeMapper;

    private final RecipeRepository recipeRepository;

    private final RecipeOnReviewRepository recipeOnReviewRepository;

    private final EmailService emailService;

    public RecipeService(RecipeOnReviewToRecipeMapper recipeMapper, RecipeRepository recipeRepository, RecipeOnReviewRepository recipeOnReviewRepository, EmailService emailService) {
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
        this.recipeOnReviewRepository = recipeOnReviewRepository;
        this.emailService = emailService;
    }


    public void acceptRecipe(RecipeOnReview recipeOnReview, String admin) {
        Recipe recipe = recipeMapper.apply(recipeOnReview);

        if (recipeOnReview.getUpdateRecipe() != null)
            recipeRepository.deleteById(recipeOnReview.getUpdateRecipe());

        recipeOnReviewRepository.deleteById(recipe.getId());
        recipeRepository.save(recipe);

        emailService.sendSimpleMail(
                new EmailDetails(recipe.getUser().getEmail(),
                        "Ваш рецепт был проверен и добавлен администратором" + admin,
                        "Добавление рецепта на сайте povarenok.ru"
                )
        );
    }


    public void declineRecipe(Long id, String declineReason, String admin) {
        String userEmail = recipeOnReviewRepository.findById(id).get().getUser().getEmail();
        recipeOnReviewRepository.deleteById(id);
        emailService.sendSimpleMail(
                new EmailDetails(userEmail,
                        "Ваш рецепт был отклонен администратором" + admin + " по причине: " + declineReason,
                        "Добавление рецепта на сайте povarenok.ru"
                )
        );
    }
}


