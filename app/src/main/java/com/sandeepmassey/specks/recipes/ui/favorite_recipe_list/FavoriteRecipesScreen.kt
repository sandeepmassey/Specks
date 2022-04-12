package com.sandeepmassey.specks.recipes.ui.favorite_recipe_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sandeepmassey.specks.core.ui.components.AppBottomBar
import com.sandeepmassey.specks.core.util.collectAsStateLifecycleAware
import com.sandeepmassey.specks.navigation.Screen

/**
 * Created by Sandeep Massey on 03-04-2022
 */
@ExperimentalMaterialApi
@Composable
fun FavoriteRecipesScreen(
    navController: NavHostController,
    favoriteRecipesViewModel: FavoriteRecipesViewModel = hiltViewModel()
) {
    val allFavoriteRecipes by
    favoriteRecipesViewModel.getAllFavoriteRecipes.collectAsStateLifecycleAware(initial = emptyList())

    Scaffold(
        topBar = {
            FavoriteRecipesTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        bottomBar = {
            AppBottomBar(
                navController = navController
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            FavoriteRecipesListContent(
                recipes = allFavoriteRecipes,
                navController = navController
            )
        }
    }
}