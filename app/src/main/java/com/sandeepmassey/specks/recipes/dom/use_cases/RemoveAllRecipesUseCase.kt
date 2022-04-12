package com.sandeepmassey.specks.recipes.dom.use_cases

import com.sandeepmassey.specks.recipes.data.repo.RecipesRepository

/**
 * Created by Sandeep Massey on 06-04-2022
 */
class RemoveAllRecipesUseCase(
    private val repository: RecipesRepository
) {
    suspend operator fun invoke() = repository.removeAllRecipes()
}