package com.sandeepmassey.specks.recipes.dom.repo

import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 22-03-2022
 */
interface RecipesLocalDataSource {

    suspend fun getSelectedRecipe(recipeId: String): Recipe

    suspend fun removeAllRecipes()

    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipe>>

    suspend fun addFavoriteRecipe(favoriteRecipe: FavoriteRecipe)

    suspend fun removeFavoriteRecipe(favoriteRecipe: FavoriteRecipe)

}