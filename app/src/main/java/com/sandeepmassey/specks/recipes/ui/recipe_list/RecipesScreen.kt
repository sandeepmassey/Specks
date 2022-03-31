package com.sandeepmassey.specks.recipes.ui.recipe_list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.sandeepmassey.specks.core.ui.AppBottomBar
import com.sandeepmassey.specks.navigation.Screen

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Composable
fun RecipesScreen(
    navController: NavHostController,
    recipesViewModel: RecipesViewModel = hiltViewModel()
) {
    val allRecipes = recipesViewModel.getAllRecipes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            RecipesTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        bottomBar = {
            AppBottomBar(
                navController = navController
            )
        },
        content = {
            RecipesListContent(
                recipes = allRecipes,
                navController = navController
            )
        }
    )
}