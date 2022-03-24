package com.sandeepmassey.specks.recipes.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sandeepmassey.specks.recipes.data.local.RecipesDatabase
import com.sandeepmassey.specks.recipes.data.page_source.RecipeRemoteMediator
import com.sandeepmassey.specks.recipes.data.page_source.SearchRecipesSource
import com.sandeepmassey.specks.recipes.data.remote.RecipesApi
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import com.sandeepmassey.specks.recipes.dom.repo.RecipesRemoteDataSource
import com.sandeepmassey.specks.recipes.dom.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@ExperimentalPagingApi
class RecipesRemoteDataSourceImpl(
    private val recipesApi: RecipesApi,
    private val recipesDatabase: RecipesDatabase
) : RecipesRemoteDataSource {

    private val recipeDao = recipesDatabase.recipeDao()

    override fun getAllRecipes(): Flow<PagingData<Recipe>> {
        val pagingSourceFactory = { recipeDao.getAllRecipes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = RecipeRemoteMediator(
                recipesApi = recipesApi,
                recipesDatabase = recipesDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchRecipes(query: String): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchRecipesSource(
                    recipesApi = recipesApi,
                    query = query
                )
            }
        ).flow
    }

}