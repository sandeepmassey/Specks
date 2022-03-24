package com.sandeepmassey.specks.recipes.ui.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.sandeepmassey.specks.navigation.Screen
import com.sandeepmassey.specks.core.ui.theme.LARGE_PADDING
import com.sandeepmassey.specks.core.ui.theme.MEDIUM_PADDING
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
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
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            //color = Color.Black.copy(alpha = ContentAlpha.medium),
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                topStart = MEDIUM_PADDING,
                bottomEnd = MEDIUM_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = recipe.yarnCount,
                    color = MaterialTheme.colors.primary,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = recipe.machine,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recipe.process,
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}