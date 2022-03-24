package com.sandeepmassey.specks.recipes.data.page_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sandeepmassey.specks.recipes.data.remote.RecipesApi
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 23-03-2022
 */
class SearchRecipesSource @Inject constructor(
    private val recipesApi: RecipesApi,
    private val query: String
) : PagingSource<Int, Recipe>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val apiResponse = recipesApi.searchRecipes(name = query)
            val recipes = apiResponse.recipes
            if (recipes.isNotEmpty()) {
                LoadResult.Page(
                    data = recipes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition
    }
}