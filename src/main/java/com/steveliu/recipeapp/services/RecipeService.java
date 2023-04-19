package com.steveliu.recipeapp.services;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.steveliu.recipeapp.commons.exceptions.RecipeNotFoundException;
import com.steveliu.recipeapp.models.Recipe;
import com.steveliu.recipeapp.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> findAllRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    public Recipe findRecipeById(Long id) throws RecipeNotFoundException {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipe.get();
        } else {
            throw new RecipeNotFoundException(id);
        }
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipeById(Long id) throws RecipeNotFoundException {
        Recipe recipe = findRecipeById(id);
        recipeRepository.delete(recipe);
    }

}


