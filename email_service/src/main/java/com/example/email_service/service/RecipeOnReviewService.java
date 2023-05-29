package com.example.email_service.service;

import com.example.data.exception.ResourceNotFoundException;
import com.example.data.model.basic.Ingredients;
import com.example.data.model.basic.RecipeOnReview;
import com.example.data.repository.basic.RecipeOnReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeOnReviewService {
    private final RecipeOnReviewRepository recipeOnReviewRepository;

    public RecipeOnReviewService(RecipeOnReviewRepository recipeOnReviewRepository) {
        this.recipeOnReviewRepository = recipeOnReviewRepository;
    }

    public RecipeOnReview findRecipeOnReviewById(Long id) {
        return recipeOnReviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Рецепт с id " + id + " не найден"));
    }
}
