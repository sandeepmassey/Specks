package com.sandeepmassey.specks.imaging.util

import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.content.Context
import android.content.IntentSender
import android.net.Uri
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.util.sdk29AndUp
import com.sandeepmassey.specks.core.util.sdk30AndUp
import com.sandeepmassey.specks.imaging.util.CameraConstants.IMAGE_FILENAME_FORMAT
import com.sandeepmassey.specks.imaging.util.CameraConstants.IMAGE_FILE_EXTENSION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Sandeep Massey on 02-05-2022
 */
val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)

//val Context.resolver: ContentResolver
//    get() = this.contentResolver

suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { listenableFuture ->
        listenableFuture.addListener(
            {
                continuation.resume(listenableFuture.get())
            },
            executor
        )
    }
}

fun getOutputDirectory(context: Context): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let { file ->
        File(file, context.resources.getString(R.string.app_name)).apply { mkdirs() }
    }
    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
}

suspend fun ImageCapture.takePicture(context: Context): Uri? {

    val fileName =
        SimpleDateFormat(IMAGE_FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
    val file = File(
        getOutputDirectory(context = context),
        "$fileName.${IMAGE_FILE_EXTENSION}"
    )
//    val relativeLocation =
//        "${Environment.DIRECTORY_PICTURES}${File.separator}${context.resources.getString(R.string.app_name)}"
//    val fileCollection = sdk29AndUp {
//        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//    } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//    val fileDetails = ContentValues().apply {
//        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
//        put(MediaStore.MediaColumns.MIME_TYPE, "image${File.separator}$IMAGE_FILE_EXTENSION")
//        sdk29AndUp {
//            put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation)
//            put(MediaStore.MediaColumns.IS_PENDING, 1)
//        }
//    }
//    val fileUri = context.resolver.insert(fileCollection, fileDetails)

    return suspendCoroutine { continuation ->
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()
//        val outputOptions = ImageCapture.OutputFileOptions.Builder(
//            context.resolver,
//            fileCollection,
//            fileDetails
//        ).build()
        takePicture(
            outputOptions,
            context.executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Timber.d("Output result: ${outputFileResults.savedUri}")
                    continuation.resume(outputFileResults.savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    Timber.d("Image capture failed: ${exception.message.toString()}")
                    continuation.resumeWithException(exception)
                }
            }
        )
//        fileDetails.clear()
//        sdk29AndUp {
//            fileDetails.put(MediaStore.MediaColumns.IS_PENDING, 0)
//        }
//        context.resolver.update(fileUri!!, fileDetails, null, null)
    }
}

suspend fun ContentResolver.delete(uri: Uri): IntentSender? {
    var intentSender: IntentSender? = null
    withContext(Dispatchers.IO) {
        try {
            delete(uri, null, null)
        } catch (e: SecurityException) {
            sdk29AndUp {
                val recoverableSecurityException = e as? RecoverableSecurityException
                intentSender = recoverableSecurityException?.userAction?.actionIntent?.intentSender
            }
            sdk30AndUp {
                intentSender = MediaStore.createDeleteRequest(this@delete, listOf(uri)).intentSender
            }
        }
    }
    return intentSender
}


