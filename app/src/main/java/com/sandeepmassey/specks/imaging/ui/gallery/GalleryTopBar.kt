package com.sandeepmassey.specks.imaging.ui.gallery

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sandeepmassey.specks.R

/**
 * Created by Sandeep Massey on 12-05-2022
 */
@Composable
fun GalleryTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.gallery_text),
                color = MaterialTheme.colors.onSurface
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}