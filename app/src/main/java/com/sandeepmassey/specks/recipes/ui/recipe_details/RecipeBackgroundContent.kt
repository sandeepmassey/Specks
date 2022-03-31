package com.sandeepmassey.specks.recipes.ui.recipe_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
import com.sandeepmassey.specks.recipes.dom.model.Recipe

/**
 * Created by Sandeep Massey on 28-03-2022
 */
@Composable
fun RecipeBackgroundContent(
    selectedRecipe: Recipe
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(all = SMALL_PADDING)
            .verticalScroll(rememberScrollState())
    ) {
        selectedRecipe.parameters.forEach { map ->
            map.forEach { entry ->
                if (entry.key == "id") {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colors.secondary),
                        text = entry.value,
                        color = MaterialTheme.colors.onSecondary,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize
                    )
                } else {
                    Text(
                        text = "${entry.key} : ${entry.value}",
                        fontSize = MaterialTheme.typography.body2.fontSize
                    )
                }
            }
        }
    }

}