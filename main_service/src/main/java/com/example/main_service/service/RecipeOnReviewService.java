package com.example.main_service.service;

import com.example.data.model.basic.*;
import com.example.data.repository.basic.RecipeOnReviewRepository;
import com.example.data.repository.basic.RecipeRepository;
import com.example.data.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.util.Optional;

@Service
public class RecipeOnReviewService {
    private final RecipeRepository recipeRepository;
    private final RecipeOnReviewRepository recipeOnReviewRepository;

    private final UserService userService;

    private final DishService dishService;

    private final IngredientsService ingredientsService;

    private final TastesService tastesService;

    private final NationalCuisineService nationalCuisineService;

    private final SendMessageService sendMessageService;

    public RecipeOnReviewService(RecipeRepository recipeRepository,
                                 RecipeOnReviewRepository recipeOnReviewRepository,
                                 UserService userService, DishService dishService,
                                 IngredientsService ingredientsService, TastesService tastesService,
                                 NationalCuisineService nationalCuisineService, SendMessageService sendMessageService) {
        this.recipeRepository = recipeRepository;
        this.recipeOnReviewRepository = recipeOnReviewRepository;
        this.userService = userService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.tastesService = tastesService;
        this.nationalCuisineService = nationalCuisineService;
        this.sendMessageService = sendMessageService;
    }

    public void saveRecipe(Long id, String admin) throws JMSException {
        Optional<RecipeOnReview> recipe = recipeOnReviewRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new ResourceNotFoundException("Рецепта с id=" + id + " не существует!");
        }
        sendMessageService.sendAcceptMessage(id, admin);

    }

    public void deleteRecipe(Long id, String admin, String declineReason) throws JMSException {
        Optional<RecipeOnReview> recipe = recipeOnReviewRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new ResourceNotFoundException("Рецепта с id=" + id + " не существует!");
        }

        sendMessageService.sendDeclineMessage(id, admin, declineReason);

    }

    public Page<RecipeOnReview> getAllRecipesOnReview(int page, int size, String sortOrder) {
        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "id"));
        return recipeOnReviewRepository.findAll(pageable);

    }
}
