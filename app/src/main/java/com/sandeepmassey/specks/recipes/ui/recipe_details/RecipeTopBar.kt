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
    onStarClicked: () -> Unit,
    onCloseClicked: () -> Unit
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
            RecipeTopBarActions(
                onStarClicked = onStarClicked,
                onCloseClicked = onCloseClicked
            )
        }
    )
}

@Composable
fun RecipeTopBarActions(
    onStarClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    StarAction(onStar = onStarClicked)
    CloseAction(onClose = onCloseClicked)
}

@Composable
fun StarAction(
    onStar: () -> Unit
) {
    IconButton(
        onClick = onStar
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = stringResource(id = R.string.star_icon)
        )
    }
}

@Composable
fun CloseAction(
    onClose: () -> Unit
) {
    IconButton(
        onClick = onClose
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(id = R.string.close_icon)
        )
    }
}