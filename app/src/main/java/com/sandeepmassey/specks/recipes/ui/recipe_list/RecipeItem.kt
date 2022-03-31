package com.sandeepmassey.specks.recipes.ui.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.components.InfoBox
import com.sandeepmassey.specks.core.ui.components.UnOrderedList
import com.sandeepmassey.specks.core.ui.theme.EXTRA_SMALL_PADDING
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
import com.sandeepmassey.specks.navigation.Screen
import com.sandeepmassey.specks.recipes.dom.model.Recipe

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Composable
fun RecipeItem(
    recipe: Recipe,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .clickable {
                navController.navigate(Screen.Recipe.passRecipeId(recipeId = recipe.id))
            }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(SMALL_PADDING)
        ) {
            Column {
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
                        bigText = recipe.yarnCount,
                        smallText = stringResource(id = R.string.count_text),
                        textColor = MaterialTheme.colors.onSurface
                    )
                    InfoBox(
                        bigText = recipe.process,
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
                        bigText = recipe.section,
                        smallText = stringResource(id = R.string.section_text),
                        textColor = MaterialTheme.colors.onSurface
                    )
                    InfoBox(
                        bigText = recipe.machine,
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
                        items = recipe.mixing,
                        textColor = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}


