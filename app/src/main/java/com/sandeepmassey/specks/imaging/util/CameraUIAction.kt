package com.sandeepmassey.specks.imaging.util

/**
 * Created by Sandeep Massey on 02-05-2022
 */
sealed class CameraUIAction {
    object OnCameraClick : CameraUIAction()
    object OnGalleryViewClick : CameraUIAction()
    object OnSwitchCameraClick : CameraUIAction()
}
