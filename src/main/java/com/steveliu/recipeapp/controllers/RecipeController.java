package com.steveliu.recipeapp.controllers;

import com.steveliu.recipeapp.models.Recipe;
import com.steveliu.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public String homePage() {
        return "home";
    }
    @GetMapping("/list")
    public String getAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAllRecipes());
        return "list";
    }

    @GetMapping("/{id}")
    public String getRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "add-recipe";
    }

    @GetMapping("/{id}/edit")
    public String editRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "edit-recipe";
    }

    @PostMapping("/save")
    public String saveOrUpdateRecipe(@ModelAttribute Recipe recipe) {
        recipeService.saveRecipe(recipe);
        return "redirect:/recipes/" + recipe.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return "redirect:/recipes/list";
    }
}
