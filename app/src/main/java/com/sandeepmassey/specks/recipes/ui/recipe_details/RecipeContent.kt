package com.sandeepmassey.specks.recipes.ui.recipe_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
import com.sandeepmassey.specks.recipes.dom.model.Recipe

/**
 * Created by Sandeep Massey on 23-03-2022
 */
@Composable
fun RecipeContent(
    selectedRecipe: Recipe?,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(all = SMALL_PADDING)
    ) {
        Text(text = selectedRecipe?.machine ?: "Unknown machine")
    }

}