package com.sandeepmassey.specks.recipes.ui.recipe_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.components.InfoBox
import com.sandeepmassey.specks.core.ui.components.UnOrderedList
import com.sandeepmassey.specks.core.ui.theme.EXTRA_SMALL_PADDING
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
import com.sandeepmassey.specks.recipes.dom.model.Recipe

/**
 * Created by Sandeep Massey on 28-03-2022
 */
@Composable
fun RecipeBottomSheetContent(
    selectedRecipe: Recipe,
    sheetBackgroundColor: Color
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(SMALL_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = SMALL_PADDING,
                    vertical = EXTRA_SMALL_PADDING
                ),
            horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            verticalAlignment = Alignment.Top
        ) {
            InfoBox(
                bigText = selectedRecipe.yarnCount,
                smallText = stringResource(id = R.string.count_text),
                textColor = MaterialTheme.colors.onSurface
            )
            InfoBox(
                bigText = selectedRecipe.process,
                smallText = stringResource(id = R.string.process_text),
                textColor = MaterialTheme.colors.onSurface
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = SMALL_PADDING,
                    vertical = EXTRA_SMALL_PADDING
                ),
            horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            verticalAlignment = Alignment.Top
        ) {
            InfoBox(
                bigText = selectedRecipe.section,
                smallText = stringResource(id = R.string.section_text),
                textColor = MaterialTheme.colors.onSurface
            )
            InfoBox(
                bigText = selectedRecipe.machine,
                smallText = stringResource(id = R.string.machine_text),
                textColor = MaterialTheme.colors.onSurface
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = SMALL_PADDING,
                    vertical = EXTRA_SMALL_PADDING
                ),
            horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            verticalAlignment = Alignment.Top
        ) {
            UnOrderedList(
                title = stringResource(id = R.string.mixing_text),
                items = selectedRecipe.mixing,
                textColor = MaterialTheme.colors.onSurface
            )
        }
    }
}