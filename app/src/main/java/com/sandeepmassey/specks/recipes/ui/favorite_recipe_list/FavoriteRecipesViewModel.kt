package com.sandeepmassey.specks.recipes.ui.favorite_recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe
import com.sandeepmassey.specks.recipes.dom.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 03-04-2022
 */
@HiltViewModel
class FavoriteRecipesViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    val getAllFavoriteRecipes = useCases.getAllFavoriteRecipesUseCase()

    fun removeFavoriteRecipe(favoriteRecipe: FavoriteRecipe) {
        viewModelScope.launch {
            useCases.removeFavoriteRecipeUseCase(favoriteRecipe = favoriteRecipe)
        }
    }
}