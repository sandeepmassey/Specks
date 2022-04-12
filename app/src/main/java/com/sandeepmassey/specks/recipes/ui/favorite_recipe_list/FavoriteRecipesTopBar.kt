package com.sandeepmassey.specks.recipes.ui.favorite_recipe_list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sandeepmassey.specks.R

/**
 * Created by Sandeep Massey on 03-04-2022
 */
@Composable
fun FavoriteRecipesTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.explore_text),
                color = MaterialTheme.colors.onSurface
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        }
    )
}