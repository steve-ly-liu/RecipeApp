package com.steveliu.recipeapp.commons.exceptions;

public class RecipeNotFoundException extends RuntimeException {
    public String id;
    public RecipeNotFoundException(Long id) {
        super("Could not find recipe with id " + id);
        this.id = String.valueOf(id);
    }
}
