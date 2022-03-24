package com.sandeepmassey.specks.recipes.data.repo

import androidx.paging.PagingData
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import com.sandeepmassey.specks.recipes.dom.repo.RecipesLocalDataSource
import com.sandeepmassey.specks.recipes.dom.repo.RecipesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 21-03-2022
 */
class RecipesRepository @Inject constructor(
    private val local: RecipesLocalDataSource,
    private val remote: RecipesRemoteDataSource
)  {

    fun getAllRecipes(): Flow<PagingData<Recipe>> {
        return remote.getAllRecipes()
    }

    fun searchRecipes(query: String): Flow<PagingData<Recipe>> {
        return remote.searchRecipes(query = query)
    }

    suspend fun getSelectedRecipe(recipeId: String): Recipe {
        return local.getSelectedRecipe(recipeId = recipeId)
    }
}