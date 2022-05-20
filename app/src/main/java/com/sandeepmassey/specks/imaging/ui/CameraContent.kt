package com.sandeepmassey.specks.imaging.ui

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sandeepmassey.specks.imaging.ui.camera.CameraCapture

/**
 * Created by Sandeep Massey on 02-05-2022
 */
@Composable
fun CameraContent(
    onImage: (Uri) -> Unit = {},
    onGallery: () -> Unit = {}
) {
    CameraCapture(
        modifier = Modifier.fillMaxSize(),
        onGallery = onGallery,
        onImageUri = onImage
    )
}
