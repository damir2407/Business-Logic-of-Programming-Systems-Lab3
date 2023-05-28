package com.example.main_service.controller;

import com.example.data.mapper.RecipeDTOMapper;
import com.example.data.mapper.RecipeOnReviewDTOMapper;
import com.example.data.dto.request.AddRecipeRequest;
import com.example.data.dto.request.UpdateRecipeRequest;
import com.example.data.dto.response.RecipeResponse;
import com.example.data.dto.response.SuccessResponse;
import com.example.data.model.basic.Recipe;
import com.example.main_service.security.AuthTokenFilter;
import com.example.main_service.security.JwtUtils;
import com.example.main_service.service.RecipeOnReviewService;
import com.example.main_service.service.RecipeService;
import com.example.main_service.util.SendMessage;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeOnReviewService recipeOnReviewService;
    private final AuthTokenFilter authTokenFilter;
    private final JwtUtils jwtUtils;
    private final SendMessage sendMessage;
    private final RecipeOnReviewDTOMapper recipeOnReviewDTOMapper;

    private final RecipeDTOMapper recipeDTOMapper;

    public RecipeController(RecipeService recipeService,
                            RecipeOnReviewService recipeOnReviewService,
                            JwtUtils jwtUtils,
                            AuthTokenFilter authTokenFilter,
                            SendMessage sendMessage, RecipeOnReviewDTOMapper recipeOnReviewDTOMapper,
                            RecipeDTOMapper recipeDTOMapper) {
        this.recipeService = recipeService;
        this.recipeOnReviewService = recipeOnReviewService;
        this.jwtUtils = jwtUtils;
        this.authTokenFilter = authTokenFilter;
        this.sendMessage = sendMessage;
        this.recipeOnReviewDTOMapper = recipeOnReviewDTOMapper;
        this.recipeDTOMapper = recipeDTOMapper;
    }

    @GetMapping("/message")
    public String sendMessage(@RequestParam String message, @RequestParam String queueMessage) throws JMSException {
        sendMessage.sendMessage(message,queueMessage);
        return message;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse newRecipe(@Valid @RequestBody AddRecipeRequest addRecipeRequest,
                                     HttpServletRequest httpServletRequest) {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        recipeService.saveRecipe(login, addRecipeRequest);
        return new SuccessResponse("Рецепт успешно отправлен на рассмотрение администраторам! Уведомление придет вам на почту.");

    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@RequestParam Long id, HttpServletRequest httpServletRequest) {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        recipeService.deleteRecipe(login, id);
    }


    @PutMapping()
    public SuccessResponse updateRecipe(@RequestParam Long id,
                                        @Valid @RequestBody UpdateRecipeRequest updateRecipeRequest,
                                        HttpServletRequest httpServletRequest) {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        recipeService.updateRecipe(login, id, updateRecipeRequest);
        return new SuccessResponse("Рецепт успешно отправлен на рассмотрение администраторам! Уведомление придет вам на почту.");
    }

    @GetMapping()
    public List<RecipeResponse> getAllRecipes(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
        return recipeService.getAllRecipes(page, size, sortOrder.toString()).getContent()
                .stream()
                .map(recipeDTOMapper)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public RecipeResponse getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.findRecipeById(id);
        return recipeDTOMapper.apply(recipe);
    }

    @PutMapping("accept/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptRecipe(@PathVariable Long id) {
        recipeOnReviewService.saveRecipe(id);
    }

    @DeleteMapping("decline/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void declineRecipe(@PathVariable Long id, @RequestBody String declineReason) {
        recipeOnReviewService.deleteRecipe(id, declineReason);
    }

    @GetMapping("/review")
    public List<RecipeResponse> getAllRecipesOnReview(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {

        return recipeOnReviewService.
                getAllRecipesOnReview(page, size, sortOrder.toString()).
                getContent()
                .stream()
                .map(recipeOnReviewDTOMapper)
                .collect(Collectors.toList());
    }
}
