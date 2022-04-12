package com.sandeepmassey.specks.recipes.ui.recipe_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import com.sandeepmassey.specks.recipes.dom.use_cases.UseCases
import com.sandeepmassey.specks.recipes.dom.util.RecipesConstants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 23-03-2022
 */
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedRecipe: MutableStateFlow<Recipe?> = MutableStateFlow(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeId = savedStateHandle.get<String>(DETAILS_ARGUMENT_KEY)
            _selectedRecipe.value = recipeId?.let {
                useCases.getSelectedRecipeUseCase(recipeId = recipeId)
            }
        }
    }

    fun addFavoriteRecipe(favoriteRecipe: FavoriteRecipe) {
        viewModelScope.launch {
            useCases.addFavoriteRecipeUseCase(favoriteRecipe = favoriteRecipe)
        }
    }
}