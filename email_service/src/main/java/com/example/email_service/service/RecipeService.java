package com.example.email_service.service;


import com.example.data.model.basic.*;
import com.example.data.repository.basic.RecipeOnReviewRepository;
import com.example.data.repository.basic.RecipeRepository;
import com.example.email_service.data.EmailDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final EmailService emailService;

    private final RecipeOnReviewService recipeOnReviewService;

    private final DishService dishService;

    private final IngredientsService ingredientsService;

    private final TastesService tastesService;

    private final UserService userService;

    private final NationalCuisineService nationalCuisineService;

    private final RecipeOnReviewRepository recipeOnReviewRepository;
    public RecipeService(RecipeRepository recipeRepository,
                         EmailService emailService, RecipeOnReviewService recipeOnReviewService, DishService dishService, IngredientsService ingredientsService, TastesService tastesService, UserService userService, NationalCuisineService nationalCuisineService, RecipeOnReviewRepository recipeOnReviewRepository) {
        this.recipeRepository = recipeRepository;
        this.emailService = emailService;
        this.recipeOnReviewService = recipeOnReviewService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.tastesService = tastesService;
        this.userService = userService;
        this.nationalCuisineService = nationalCuisineService;
        this.recipeOnReviewRepository = recipeOnReviewRepository;
    }


    public void acceptRecipe(Long recipeId, String admin) {
        RecipeOnReview recipeOnReview = recipeOnReviewService.findRecipeOnReviewById(recipeId);
        Recipe recipe = new Recipe();
        Dish dish = dishService.findDishByName(recipeOnReview.getDish().getName());
        User user = userService.findUserByLogin(recipeOnReview.getUser().getLogin());
        NationalCuisine nationalCuisine = nationalCuisineService.findNationalCuisineByName(recipeOnReview.getNationalCuisine().getCuisine());
        List<Tastes> tastesList = tastesService.findAllTastesByTasteNames(recipeOnReview.getAllTastesName());
        List<Ingredients> ingredientsList = ingredientsService.findAllIngredientsByNames(recipeOnReview.getAllIngredientsName());
        recipe.setDish(dish);
        recipe.setDescription(recipeOnReview.getDescription());
        recipe.setId(recipeOnReview.getId());
        recipe.setTastes(tastesList);
        recipe.setIngredients(ingredientsList);
        recipe.setCountPortion(recipeOnReview.getCountPortion());
        recipe.setNationalCuisine(nationalCuisine);
        recipe.setUser(user);

        if (recipeOnReview.getUpdateRecipe() != null) {
            recipeRepository.deleteById(recipeOnReview.getUpdateRecipe());
        }
        recipeOnReviewRepository.deleteById(recipeId);

        emailService.sendSimpleMail(
                new EmailDetails(recipe.getUser().getEmail(),
                        "Ваш рецепт был проверен и добавлен администратором" + admin,
                        "Добавление рецепта на сайте povarenok.ru"
                )
        );


    }


    public void declineRecipe(Long recipeId, String admin, String declineReason) {
        String userEmail = recipeOnReviewRepository.findById(recipeId).get().getUser().getEmail();
        recipeOnReviewRepository.deleteById(recipeId);
        emailService.sendSimpleMail(
                new EmailDetails(userEmail,
                        "Ваш рецепт был отклонен администратором" + admin + " по причине: " + declineReason,
                        "Добавление рецепта на сайте povarenok.ru"
                )
        );
    }
}


