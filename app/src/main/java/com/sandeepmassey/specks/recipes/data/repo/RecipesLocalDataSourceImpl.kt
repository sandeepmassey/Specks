package com.sandeepmassey.specks.recipes.data.repo

import com.sandeepmassey.specks.recipes.data.local.RecipesDatabase
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import com.sandeepmassey.specks.recipes.dom.repo.RecipesLocalDataSource

/**
 * Created by Sandeep Massey on 22-03-2022
 */
class RecipesLocalDataSourceImpl(
    recipesDatabase: RecipesDatabase
): RecipesLocalDataSource {

    private val recipeDao = recipesDatabase.recipeDao()

    override suspend fun getSelectedRecipe(recipeId: String): Recipe {
        return recipeDao.getSelectedRecipe(recipeId = recipeId)
    }

}