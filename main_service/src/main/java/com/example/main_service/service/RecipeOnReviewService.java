package com.example.main_service.service;

import com.example.data.model.basic.*;
import com.example.data.repository.basic.RecipeOnReviewRepository;
import com.example.data.repository.basic.RecipeRepository;
import com.example.main_service.exception.PermissionDeniedException;
import com.example.main_service.security.JwtUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public RecipeOnReviewService(RecipeRepository recipeRepository,
                                 RecipeOnReviewRepository recipeOnReviewRepository,
                                 UserService userService, DishService dishService,
                                 IngredientsService ingredientsService, TastesService tastesService,
                                 NationalCuisineService nationalCuisineService) {
        this.recipeRepository = recipeRepository;
        this.recipeOnReviewRepository = recipeOnReviewRepository;
        this.userService = userService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.tastesService = tastesService;
        this.nationalCuisineService = nationalCuisineService;
    }

    public void saveRecipe(Long id) {
        Optional<RecipeOnReview> recipe = recipeOnReviewRepository.findById(id);


        if (recipe.isEmpty()) {
            throw new PermissionDeniedException("Рецепта с id=" + id + " не существует!");
        }



        //     тут в очередь recipe.accept.queue передай recipe.get() ибо прошел уже проверки + логин админа с токена




//        коменты снизу не удаляй, мне мб понадобятся
//        Recipe recipe1 = new Recipe();
//        Dish dish = dishService.findDishByName(recipe.get().getDish().getName());
//        User user = userService.findUserByLogin(recipe.get().getUser().getLogin());
//        NationalCuisine nationalCuisine = nationalCuisineService.findNationalCuisineByName(recipe.get().getNationalCuisine().getCuisine());
//        List<Tastes> tastesList = tastesService.findAllTastesByTasteNames(recipe.get().getAllTastesName());
//        List<Ingredients> ingredientsList = ingredientsService.findAllIngredientsByNames(recipe.get().getAllIngredientsName());
//        recipe1.setDish(dish);
//        recipe1.setDescription(recipe.get().getDescription());
//        recipe1.setId(recipe.get().getId());
//        recipe1.setTastes(tastesList);
//        recipe1.setIngredients(ingredientsList);
//        recipe1.setCountPortion(recipe.get().getCountPortion());
//        recipe1.setNationalCuisine(nationalCuisine);
//        recipe1.setUser(user);
//        if (recipe.get().getUpdateRecipe() != null) {
//            recipeRepository.deleteById(recipe.get().getUpdateRecipe());
//        }
//        recipeOnReviewRepository.deleteById(id);
//        return recipeRepository.save(recipe1);

    }

    public void deleteRecipe(Long id, String declineReason) {
        Optional<RecipeOnReview> recipe = recipeOnReviewRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new PermissionDeniedException("Рецепта с id=" + id + " не существует!");
        }


        //     тут в очередь recipe.decline.queue передай id ибо прошел уже проверки + причину отказа
        //     + логин админа с токена

//        тож не удаляй это
//        recipeOnReviewRepository.deleteById(id);
    }

    public Page<RecipeOnReview> getAllRecipesOnReview(int page, int size, String sortOrder) {
        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "id"));
        return recipeOnReviewRepository.findAll(pageable);

    }
}
