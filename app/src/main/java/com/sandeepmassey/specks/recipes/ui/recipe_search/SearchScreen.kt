package com.sandeepmassey.specks.recipes.ui.recipe_search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.sandeepmassey.specks.recipes.ui.recipe_list.RecipesListContent

/**
 * Created by Sandeep Massey on 23-03-2022
 */
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedRecipes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchRecipes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            RecipesListContent(
                recipes = heroes,
                navController = navController
            )
        }
    )
}