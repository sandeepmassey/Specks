package com.sandeepmassey.specks.imaging.ui.camera

import android.net.Uri
import android.view.Surface
import androidx.camera.core.AspectRatio.RATIO_16_9
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.sandeepmassey.specks.imaging.util.CameraUIAction
import com.sandeepmassey.specks.imaging.util.getCameraProvider
import com.sandeepmassey.specks.imaging.util.takePicture
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Sandeep Massey on 06-05-2022
 */
@Composable
fun CameraCapture(
    modifier: Modifier = Modifier,
    onImageUri: (Uri) -> Unit = {},
    onGallery: () -> Unit = {}
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    val cameraSelector =
        CameraSelector.Builder().requireLensFacing(lensFacing).build()
    var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder()
                .setTargetAspectRatio(RATIO_16_9)
                .setTargetRotation(Surface.ROTATION_0)
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build())
    }

    Box(
        modifier = modifier,
    ) {
        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            onUseCase = { useCase ->
                previewUseCase = useCase
            }
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            CameraControlsBar { cameraUIAction ->
                when (cameraUIAction) {
                    // Flip cameras
                    is CameraUIAction.OnSwitchCameraClick -> {
                        lensFacing =
                            if (lensFacing == CameraSelector.LENS_FACING_BACK)
                                CameraSelector.LENS_FACING_FRONT
                            else
                                CameraSelector.LENS_FACING_BACK
                    }
                    // Capture photo
                    is CameraUIAction.OnCameraClick -> {
                        coroutineScope.launch {
                            imageCaptureUseCase.takePicture(context)?.let { uri ->
                                onImageUri(uri)
                            }
                        }
                    }
                    // View photo gallery
                    is CameraUIAction.OnGalleryViewClick -> {
                        onGallery()
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = lensFacing) {
        val cameraProvider = context.getCameraProvider()
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                previewUseCase,
                imageCaptureUseCase
            )
        } catch (e: Exception) {
            Timber.d("CameraCapture Failed to bind camera use cases ${e.message.toString()}")
        }
    }
}