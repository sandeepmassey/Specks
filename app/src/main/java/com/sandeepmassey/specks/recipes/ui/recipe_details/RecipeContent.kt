package com.sandeepmassey.specks.recipes.ui.recipe_details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.sandeepmassey.specks.core.ui.theme.EXPANDED_RADIUS_LEVEL
import com.sandeepmassey.specks.core.ui.theme.EXTRA_LARGE_PADDING
import com.sandeepmassey.specks.core.ui.theme.MIN_SHEET_HEIGHT
import com.sandeepmassey.specks.recipes.dom.model.Recipe

/**
 * Created by Sandeep Massey on 23-03-2022
 */
@ExperimentalMaterialApi
@Composable
fun RecipeContent(
    selectedRecipe: Recipe?,
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Expanded
        )
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedRecipe?.let {
                RecipeBottomSheetContent(
                    selectedRecipe = it,
                    sheetBackgroundColor = MaterialTheme.colors.surface
                )
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            selectedRecipe?.let { recipe ->
                RecipeBackgroundContent(
                    selectedRecipe = recipe
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

