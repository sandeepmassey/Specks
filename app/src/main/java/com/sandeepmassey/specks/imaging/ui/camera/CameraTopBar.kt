package com.sandeepmassey.specks.imaging.ui.camera

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sandeepmassey.specks.R

/**
 * Created by Sandeep Massey on 02-05-2022
 */
@Composable
fun CameraTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.camera_text),
                color = MaterialTheme.colors.onSurface
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}