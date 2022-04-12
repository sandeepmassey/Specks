package com.sandeepmassey.specks.recipes.dom.use_cases

/**
 * Created by Sandeep Massey on 21-03-2022
 */
data class UseCases(
    val getAllRecipesUseCase: GetAllRecipesUseCase,
    val getSelectedRecipeUseCase: GetSelectedRecipeUseCase,
    val searchRecipesUseCase: SearchRecipesUseCase,
    val removeAllRecipesUseCase: RemoveAllRecipesUseCase,
    val getAllFavoriteRecipesUseCase: GetAllFavoriteRecipesUseCase,
    val addFavoriteRecipeUseCase: AddFavoriteRecipeUseCase,
    val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCase,
)
