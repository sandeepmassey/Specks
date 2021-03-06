package com.sandeepmassey.specks.recipes.ui.recipe_details

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.sandeepmassey.specks.navigation.Screen

/**
 * Created by Sandeep Massey on 23-03-2022
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@Composable
fun RecipeScreen(
    navController: NavHostController,
    recipeViewModel: RecipeViewModel = hiltViewModel(),
) {
    val selectedRecipe by recipeViewModel.selectedRecipe.collectAsState()

    Scaffold(
        topBar = {
            RecipeTopBar(
                onStarClicked = {
                    selectedRecipe?.let { recipe ->
                        recipeViewModel.addFavoriteRecipe(favoriteRecipe = recipe.toFavoriteRecipe())
                    }
                    navigateToFavoriteRecipesScreen(navController = navController)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            RecipeContent(
                selectedRecipe = selectedRecipe
            )
        }
    )
}

private fun navigateToFavoriteRecipesScreen(
    navController: NavHostController
) {
    navController.popBackStack(route = Screen.Recipe.route, inclusive = true)
    navController.navigate(route = Screen.FavoriteRecipes.route)
}