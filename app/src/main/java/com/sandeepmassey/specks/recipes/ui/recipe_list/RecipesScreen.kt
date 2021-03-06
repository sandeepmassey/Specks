package com.sandeepmassey.specks.recipes.ui.recipe_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.sandeepmassey.specks.core.ui.components.AppBottomBar
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
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecipesListContent(
                recipes = allRecipes,
                navController = navController
            )
        }
    }
}