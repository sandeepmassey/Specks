package com.sandeepmassey.specks.imaging.ui.gallery

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.imaging.util.CameraConstants.EXTENSION_WHITELIST
import com.sandeepmassey.specks.imaging.util.GalleryUIAction
import com.sandeepmassey.specks.imaging.util.getOutputDirectory
import java.util.*

/**
 * Created by Sandeep Massey on 11-05-2022
 */
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun GalleryContent(
    onNavigateBack: () -> Unit = {},
    onDelete: (Uri) -> Unit = {}
) {
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
            getOutputDirectory(context = context).listFiles()?.let { filesList ->
                val mediaList = filesList.sortedDescending().toMutableList().onEach { file ->
                    EXTENSION_WHITELIST.contains(file.extension.uppercase(Locale.ROOT))
                }
                HorizontalPager(
                    count = mediaList.size,
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page: Int ->
                    imageUri.value = mediaList[page].toUri()
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUri.value)
                            .crossfade(false)
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_placeholder),
                        contentDescription = stringResource(id = R.string.captured_image_text),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            GalleryControlsBar { galleryUIAction ->
                when (galleryUIAction) {
                    is GalleryUIAction.OnShareClick -> {

                    }
                    is GalleryUIAction.OnDeleteClick -> {
                        imageUri.value?.let { uri ->
                            onDelete(uri)
                        }
                    }
                    is GalleryUIAction.OnBackClick -> {
                        onNavigateBack()
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.collect { page ->
            pagerState.animateScrollToPage(page)
        }
    }
}
