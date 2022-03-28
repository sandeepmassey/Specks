package com.sandeepmassey.specks.recipes.ui.recipe_details

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.sandeepmassey.specks.core.ui.theme.MIN_SHEET_HEIGHT
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
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
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = SMALL_PADDING,
            topEnd = SMALL_PADDING
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedRecipe?.let {
                RecipeBottomSheetContent(
                    selectedRecipe = it,
                    sheetBackgroundColor = MaterialTheme.colors.surface.copy(
                        alpha = ContentAlpha.medium
                    )
                )
            }
        }
    ) {
        selectedRecipe?.let { recipe ->
            RecipeBackgroundContent(selectedRecipe = recipe)
        }
    }

}

