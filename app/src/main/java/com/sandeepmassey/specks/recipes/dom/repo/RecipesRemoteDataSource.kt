package com.sandeepmassey.specks.recipes.dom.repo

import androidx.paging.PagingData
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 21-03-2022
 */
interface RecipesRemoteDataSource {

    fun getAllRecipes(): Flow<PagingData<Recipe>>

    fun searchRecipes(query: String): Flow<PagingData<Recipe>>
}