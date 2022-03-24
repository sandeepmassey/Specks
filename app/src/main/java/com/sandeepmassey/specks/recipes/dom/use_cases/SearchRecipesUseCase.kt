package com.sandeepmassey.specks.recipes.dom.use_cases

import androidx.paging.PagingData
import com.sandeepmassey.specks.recipes.data.repo.RecipesRepository
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 23-03-2022
 */
class SearchRecipesUseCase(
    private val repository: RecipesRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Recipe>> {
        return repository.searchRecipes(query = query)
    }
}