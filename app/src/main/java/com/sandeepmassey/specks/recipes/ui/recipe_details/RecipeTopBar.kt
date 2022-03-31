package com.sandeepmassey.specks.recipes.ui.recipe_details

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sandeepmassey.specks.R

/**
 * Created by Sandeep Massey on 23-03-2022
 */
@Composable
fun RecipeTopBar(
    onCloseClicked: () -> Unit,
    onStarClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.recipe_text),
                color = MaterialTheme.colors.onSurface
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        actions = {
            IconButton(onClick = onStarClicked) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(id = R.string.star_icon)
                )
            }
            IconButton(onClick = onCloseClicked) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon)
                )
            }
        }
    )
}