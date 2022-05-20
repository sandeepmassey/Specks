package com.sandeepmassey.specks.imaging.ui

import android.app.Application
import android.graphics.Rect
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 07-05-2022
 */
@HiltViewModel
class CameraViewModel @Inject constructor(
    application: Application,
    useCases: com.sandeepmassey.specks.imaging.dom.use_cases.UseCases,
) : AndroidViewModel(application) {

    private val _processed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val processed: StateFlow<Boolean> = _processed

    private val _windowMetrics: MutableState<Rect> = mutableStateOf(Rect())
    val windowMetrics: State<Rect> = _windowMetrics

    private val trashDetector = useCases.getTrashDetectorUseCase

    fun analyzePhoto(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("File path is: ${uri.path}")
            val matRGBA = Imgcodecs.imread(uri.path)
            // Process image
            trashDetector().processedRGBAImage(matRGBA)
            // Write results on image
            Imgproc.putText(
                matRGBA,
                "Size ${matRGBA.rows() * matRGBA.cols()} Win ${windowMetrics.value.width()}",
                Point(150.0, 500.0),
                2,
                4.0,
                Scalar(0.0, 255.0, 0.0),
                2
            )
            Imgcodecs.imwrite(uri.path, matRGBA)
            _processed.value = true
            Timber.d("Status $processed")
            matRGBA.release()
        }
    }
}