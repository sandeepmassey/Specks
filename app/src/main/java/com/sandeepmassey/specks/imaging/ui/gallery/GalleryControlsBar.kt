package com.sandeepmassey.specks.imaging.ui.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING
import com.sandeepmassey.specks.imaging.ui.ImagingControl
import com.sandeepmassey.specks.imaging.util.GalleryUIAction

/**
 * Created by Sandeep Massey on 10-05-2022
 */
@Composable
fun GalleryControlsBar(
    galleryUIAction: (GalleryUIAction) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent.copy(alpha = 0.20f))
            .padding(SMALL_PADDING),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically)
    {
        // Share
        ImagingControl(
            imageVector = Icons.Default.Share,
            contentDescId = R.string.share_image_text,
            modifier = Modifier.size(36.dp),
            onClick = { galleryUIAction(GalleryUIAction.OnShareClick) }
        )
        // Delete
        ImagingControl(
            imageVector = Icons.Default.Delete,
            contentDescId = R.string.delete_image_text,
            modifier = Modifier.size(36.dp),
            onClick = { galleryUIAction(GalleryUIAction.OnDeleteClick) }
        )
        // Back
        ImagingControl(
            imageVector = Icons.Default.ArrowBackIos,
            contentDescId = R.string.back_to_camera_text,
            modifier = Modifier.size(36.dp),
            onClick = { galleryUIAction(GalleryUIAction.OnBackClick) }
        )
    }
}