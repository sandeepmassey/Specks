package com.sandeepmassey.specks.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Sandeep Massey on 18-03-2022
 */
sealed class Screen(
    val route: String,
    val title: String?,
    val icon_outlined: ImageVector?,
    val icon_filled: ImageVector?
) {
    object Login : Screen(
        route = "login_screen",
        title = null,
        icon_outlined = null,
        icon_filled = null
    )

    object Profile : Screen(
        route = "profile_screen",
        title = "Profile",
        icon_outlined = Icons.Outlined.AccountCircle,
        icon_filled = Icons.Filled.AccountCircle
    )

    object Recipes : Screen(
        route = "recipes_screen",
        title = "Recipes",
        icon_outlined = Icons.Outlined.MenuBook,
        icon_filled = Icons.Filled.MenuBook
    )

    object Recipe : Screen(
        route = "recipe_screen/{recipeId}",
        title = null,
        icon_outlined = null,
        icon_filled = null
    ) {
        fun passRecipeId(recipeId: String): String {
            return "recipe_screen/$recipeId"
        }
    }

    object Search : Screen(
        route = "search_screen",
        title = null,
        icon_outlined = null,
        icon_filled = null
    )

    object FavoriteRecipes : Screen(
        route = "favorite_recipes_screen",
        title = "Favorites",
        icon_outlined = Icons.Outlined.Favorite,
        icon_filled = Icons.Filled.Favorite
    )

    object Camera : Screen(
        route = "camera_screen",
        title = "Camera",
        icon_outlined = Icons.Outlined.Camera,
        icon_filled = Icons.Filled.Camera
    )

    object Gallery : Screen(
        route = "gallery_screen",
        title = "Gallery",
        icon_outlined = null,
        icon_filled = null
    )
}