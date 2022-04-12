package com.sandeepmassey.specks.recipes.dom.use_cases

import androidx.paging.PagingData
import com.sandeepmassey.specks.recipes.data.repo.RecipesRepository
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 21-03-2022
 */
class GetAllRecipesUseCase(
    private val repository: RecipesRepository
) {
    operator fun invoke(): Flow<PagingData<Recipe>> = repository.getAllRecipes()
}