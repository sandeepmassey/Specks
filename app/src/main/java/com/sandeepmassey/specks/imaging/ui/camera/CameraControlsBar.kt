package com.sandeepmassey.specks.imaging.ui.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.FlipCameraAndroid
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material.icons.sharp.PhotoLibrary
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
import com.sandeepmassey.specks.imaging.ui.ImagingControl
import com.sandeepmassey.specks.imaging.util.CameraUIAction

/**
 * Created by Sandeep Massey on 02-05-2022
 */
@Composable
fun CameraControlsBar(
    cameraUIAction: (CameraUIAction) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(SMALL_PADDING),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Switch camera
        ImagingControl(
            imageVector = Icons.Sharp.FlipCameraAndroid,
            contentDescId = R.string.switch_camera_text,
            modifier = Modifier.size(48.dp),
            onClick = { cameraUIAction(CameraUIAction.OnSwitchCameraClick) }
        )
        // Click
        ImagingControl(
            imageVector = Icons.Sharp.Lens,
            contentDescId = R.string.click_camera_text,
            modifier = Modifier
                .size(64.dp)
                .padding(1.dp)
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = Color.White
                ),
            onClick = { cameraUIAction(CameraUIAction.OnCameraClick) }
        )
        // View photo gallery
        ImagingControl(
            imageVector = Icons.Sharp.PhotoLibrary,
            contentDescId = R.string.gallery_photo_text,
            modifier = Modifier.size(48.dp),
            onClick = { cameraUIAction(CameraUIAction.OnGalleryViewClick) }
        )
    }
}