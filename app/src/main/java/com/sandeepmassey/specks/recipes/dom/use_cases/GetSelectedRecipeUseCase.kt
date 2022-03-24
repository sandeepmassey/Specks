package com.sandeepmassey.specks.recipes.dom.use_cases

import com.sandeepmassey.specks.recipes.data.repo.RecipesRepository
import com.sandeepmassey.specks.recipes.dom.model.Recipe

/**
 * Created by Sandeep Massey on 23-03-2022
 */
class GetSelectedRecipeUseCase(
    private val repository: RecipesRepository
) {
    suspend operator fun invoke(recipeId: String): Recipe {
        return repository.getSelectedRecipe(recipeId = recipeId)
    }
}