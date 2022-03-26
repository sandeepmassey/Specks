package com.sandeepmassey.specks.recipes.ui.recipe_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import com.sandeepmassey.specks.recipes.ui.common.EmptyScreen

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Composable
fun RecipesListContent(
    recipes: LazyPagingItems<Recipe>,
    navController: NavHostController
) {

    val result = handlePagingResult(recipes = recipes)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = recipes,
                key = { recipe ->
                    recipe.id
                }
            ) { recipe ->
                recipe?.let {
                    RecipeItem(
                        recipe = it,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    recipes: LazyPagingItems<Recipe>
): Boolean {
    recipes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                LinearProgressIndicator()
                false
            }
            error != null -> {
                EmptyScreen(error = error)
                false
            }
            recipes.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}
