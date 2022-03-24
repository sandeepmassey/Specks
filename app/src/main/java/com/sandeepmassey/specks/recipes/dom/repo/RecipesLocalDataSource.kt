package com.sandeepmassey.specks.recipes.dom.repo

import com.sandeepmassey.specks.recipes.dom.model.Recipe

/**
 * Created by Sandeep Massey on 22-03-2022
 */
interface RecipesLocalDataSource {

    suspend fun getSelectedRecipe(recipeId: String): Recipe

}