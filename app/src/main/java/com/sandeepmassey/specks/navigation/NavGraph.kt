package com.sandeepmassey.specks.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.sandeepmassey.specks.auth.ui.login.LoginScreen
import com.sandeepmassey.specks.auth.ui.profile.ProfileScreen
import com.sandeepmassey.specks.imaging.ui.CameraScreen
import com.sandeepmassey.specks.imaging.ui.gallery.GalleryScreen
import com.sandeepmassey.specks.recipes.dom.util.RecipesConstants.DETAILS_ARGUMENT_KEY
import com.sandeepmassey.specks.recipes.ui.favorite_recipe_list.FavoriteRecipesScreen
import com.sandeepmassey.specks.recipes.ui.recipe_details.RecipeScreen
import com.sandeepmassey.specks.recipes.ui.recipe_list.RecipesScreen
import com.sandeepmassey.specks.recipes.ui.recipe_search.SearchScreen

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Login
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        // Profile
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        // Recipes
        composable(route = Screen.Recipes.route) {
            RecipesScreen(navController = navController)
        }
        // Search
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        // Recipe
        composable(
            route = Screen.Recipe.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) {
            RecipeScreen(navController = navController)
        }
        // Favorite recipes
        composable(route = Screen.FavoriteRecipes.route) {
            FavoriteRecipesScreen(navController = navController)
        }
        // Camera
        composable(route = Screen.Camera.route) {
            CameraScreen(
                navController = navController
            )
        }
        // Gallery
        composable(route = Screen.Gallery.route) {
            GalleryScreen(
                navController = navController
            )
        }
    }
}