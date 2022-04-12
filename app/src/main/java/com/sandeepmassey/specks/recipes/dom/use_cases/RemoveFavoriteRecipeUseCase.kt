package com.sandeepmassey.specks.recipes.dom.use_cases

import com.sandeepmassey.specks.recipes.data.repo.RecipesRepository
import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe

/**
 * Created by Sandeep Massey on 10-04-2022
 */
class RemoveFavoriteRecipeUseCase(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke(favoriteRecipe: FavoriteRecipe) {
        return repository.removeFavoriteRecipe(favoriteRecipe = favoriteRecipe)
    }
}