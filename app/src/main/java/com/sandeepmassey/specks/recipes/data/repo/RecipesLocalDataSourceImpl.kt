package com.sandeepmassey.specks.recipes.data.repo

import com.sandeepmassey.specks.recipes.data.local.RecipesDatabase
import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import com.sandeepmassey.specks.recipes.dom.repo.RecipesLocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 22-03-2022
 */
class RecipesLocalDataSourceImpl(
    recipesDatabase: RecipesDatabase
): RecipesLocalDataSource {

    private val recipeDao = recipesDatabase.recipeDao()

    override suspend fun getSelectedRecipe(recipeId: String): Recipe =
        recipeDao.getSelectedRecipe(recipeId = recipeId)

    override suspend fun removeAllRecipes() =
        recipeDao.removeAllRecipes()

    override fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipe>> =
        recipeDao.getAllFavoriteRecipes()

    override suspend fun addFavoriteRecipe(favoriteRecipe: FavoriteRecipe) {
        recipeDao.addFavoriteRecipe(favoriteRecipe = favoriteRecipe)
    }

    override suspend fun removeFavoriteRecipe(favoriteRecipe: FavoriteRecipe) {
        recipeDao.removeFavoriteRecipe(favoriteRecipe = favoriteRecipe)
    }

}