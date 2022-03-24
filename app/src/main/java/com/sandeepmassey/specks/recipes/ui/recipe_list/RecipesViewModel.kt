package com.sandeepmassey.specks.recipes.ui.recipe_list

import androidx.lifecycle.ViewModel
import com.sandeepmassey.specks.recipes.dom.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@HiltViewModel
class RecipesViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllRecipes = useCases.getAllRecipesUseCase()
}