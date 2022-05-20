package com.sandeepmassey.specks.imaging.ui.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.net.toFile
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sandeepmassey.specks.navigation.Screen

/**
 * Created by Sandeep Massey on 11-05-2022
 */
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun GalleryScreen(
    navController: NavHostController,
) {

    Scaffold(
        topBar = {
            GalleryTopBar()
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GalleryContent(
                    onDelete = {
                        it.toFile().delete()
                    },
                    onNavigateBack = {
                        navigateToCameraScreen(navController = navController)
                    }
                )
            }
        }
    )
}

private fun navigateToCameraScreen(
    navController: NavHostController,
) {
    navController.popBackStack(route = Screen.Gallery.route, inclusive = true)
    navController.navigate(route = Screen.Camera.route)
}