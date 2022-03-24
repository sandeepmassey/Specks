package com.sandeepmassey.specks.recipes.ui.recipe_search

import androidx.compose.runtime.Composable
import com.sandeepmassey.specks.core.ui.SearchWidget

/**
 * Created by Sandeep Massey on 23-03-2022
 */
@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )
}