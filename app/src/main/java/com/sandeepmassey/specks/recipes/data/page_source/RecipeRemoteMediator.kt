package com.sandeepmassey.specks.recipes.data.page_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sandeepmassey.specks.recipes.data.local.RecipesDatabase
import com.sandeepmassey.specks.recipes.data.remote.RecipesApi
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import com.sandeepmassey.specks.recipes.dom.model.RecipeRemoteKeys
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@ExperimentalPagingApi
class RecipeRemoteMediator @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipesDatabase: RecipesDatabase
) : RemoteMediator<Int, Recipe>() {

    private val recipeDao = recipesDatabase.recipeDao()
    private val recipeRemoteKeysDao = recipesDatabase.recipeRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = recipeRemoteKeysDao.getRemoteKeys(id = "1")?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Recipe>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val lastPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    lastPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = recipesApi.getAllRecipes(page = page)
            if (response.recipes.isNotEmpty()) {
                recipesDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        recipeDao.removeAllRecipes()
                        recipeRemoteKeysDao.removeAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.recipes.map { recipe ->
                        RecipeRemoteKeys(
                            id = recipe.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    recipeRemoteKeysDao.addAllRemoteKeys(recipeRemoteKeys = keys)
                    recipeDao.addRecipes(recipes = response.recipes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Recipe>
    ): RecipeRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                recipeRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Recipe>
    ): RecipeRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { recipe ->
                recipeRemoteKeysDao.getRemoteKeys(id = recipe.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Recipe>
    ): RecipeRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { recipe ->
                recipeRemoteKeysDao.getRemoteKeys(id = recipe.id)
            }
    }

}