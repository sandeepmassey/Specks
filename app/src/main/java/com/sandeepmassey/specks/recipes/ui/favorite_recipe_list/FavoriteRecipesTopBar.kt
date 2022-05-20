package com.sandeepmassey.specks.recipes.ui.favorite_recipe_list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sandeepmassey.specks.R

/**
 * Created by Sandeep Massey on 03-04-2022
 */
@Composable
fun FavoriteRecipesTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.favorites_text),
                color = MaterialTheme.colors.onSurface
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}