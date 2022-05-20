package com.sandeepmassey.specks.recipes.ui.favorite_recipe_list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
    onSwipe: (FavoriteRecipe) -> Unit
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
                        onSwipe(item)
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = state,
                directions = setOf(DismissDirection.EndToStart),
                dismissThresholds = { FractionalThreshold(0.33f) },
                background = {
                    val direction = state.dismissDirection ?: return@SwipeToDismiss
                    val color by animateColorAsState(
                        targetValue = when (state.targetValue) {
                            DismissValue.Default -> Color.Transparent
                            DismissValue.DismissedToStart -> MaterialTheme.colors.error
                            else -> Color.Transparent
                        }
                    )
                    val icon = when (direction) {
                        DismissDirection.EndToStart -> Icons.Default.Delete
                        else -> Icons.Default.Circle
                    }
                    val scale by animateFloatAsState(
                        targetValue = if (state.targetValue == DismissValue.Default) 0.8f else 1.2f
                    )
                    val alignment = when (direction) {
                        DismissDirection.EndToStart -> Alignment.CenterEnd
                        DismissDirection.StartToEnd -> Alignment.CenterStart
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = color)
                            .padding(SMALL_PADDING),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = stringResource(id = R.string.swipe_icon),
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
                dismissContent = {
                    FavoriteRecipeItem(
                        recipe = item,
                        navController = navController
                    )
                }
            )
        }
    }

}