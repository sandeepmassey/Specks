package com.sandeepmassey.specks.recipes.ui.favorite_recipe_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe
/**
 * Created by Sandeep Massey on 03-04-2022
 */
@ExperimentalMaterialApi
@Composable
fun FavoriteRecipesListContent(
    recipes: List<FavoriteRecipe>,
    navController: NavHostController,
) {

    val items = remember { mutableStateListOf<FavoriteRecipe>() }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(1F),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        itemsIndexed(
            items = recipes,
            key = { _, recipe ->
                recipe.hashCode()
            }
        ) { _, item ->
            val state = rememberDismissState(
                confirmStateChange = { value ->
                    if (value == DismissValue.DismissedToStart) {
                        items.remove(item)
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = state,
                background = {
                    val color = when (state.dismissDirection) {
                        DismissDirection.StartToEnd -> Color.Transparent
                        DismissDirection.EndToStart -> Color.Red
                        null -> Color.Transparent
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = color)
                            .padding(
                                SMALL_PADDING)
                    ) {
                        Icon(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(id = R.string.delete_icon)
                        )
                    }
                },
                dismissContent = {
                    FavoriteRecipeItem(
                        recipe = item,
                        navController = navController
                    )
                },
                directions = setOf(DismissDirection.EndToStart)
            )


        }
    }

}