package com.sandeepmassey.specks.recipes.dom.use_cases

import com.sandeepmassey.specks.recipes.data.repo.RecipesRepository
import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 03-04-2022
 */
class GetAllFavoriteRecipesUseCase(
    private val repository: RecipesRepository
) {
    operator fun invoke(): Flow<List<FavoriteRecipe>> = repository.getAllFavoriteRecipes()
}