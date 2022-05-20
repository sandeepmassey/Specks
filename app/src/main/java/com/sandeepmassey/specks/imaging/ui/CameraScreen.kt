package com.sandeepmassey.specks.imaging.ui

import android.Manifest.permission.CAMERA
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.components.AppBottomBar
import com.sandeepmassey.specks.imaging.ui.camera.CameraTopBar
import com.sandeepmassey.specks.navigation.Screen
import com.sandeepmassey.specks.permission.PermissionsUI
import timber.log.Timber

/**
 * Created by Sandeep Massey on 02-05-2022
 */
@ExperimentalPermissionsApi
@Composable
fun CameraScreen(
    navController: NavHostController,
    cameraViewModel: CameraViewModel = hiltViewModel(),
) {
    val processCompleted by cameraViewModel.processed.collectAsState()
    val windowInfo by cameraViewModel.windowMetrics

    Scaffold(
        topBar = {
            CameraTopBar()
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PermissionsUI(
                    permission = CAMERA,
                    rationale = stringResource(
                        id = R.string.permission_rationale_camera_text
                    ),
                    warning = stringResource(
                        id = R.string.permission_warning_camera_text
                    )
                ) {
                    CameraContent(
                        onImage = { uri ->
                            cameraViewModel.analyzePhoto(uri)
                            Timber.d(processCompleted.toString())
                            navigateToGalleryScreen(navController = navController)
                        },
                        onGallery = {
                            navigateToGalleryScreen(navController = navController)
                        }
                    )
                }
            }
        },
        bottomBar = {
            AppBottomBar(navController = navController)
        }
    )
}

private fun navigateToGalleryScreen(
    navController: NavHostController,
) {
    navController.popBackStack(route = Screen.Camera.route, inclusive = true)
    navController.navigate(route = Screen.Gallery.route)
}