package com.sandeepmassey.specks.recipes.dom.use_cases

import com.sandeepmassey.specks.recipes.data.repo.RecipesRepository
import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe

/**
 * Created by Sandeep Massey on 04-04-2022
 */
class AddFavoriteRecipeUseCase(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke(favoriteRecipe: FavoriteRecipe) {
        return repository.addFavoriteRecipe(favoriteRecipe = favoriteRecipe)
    }
}