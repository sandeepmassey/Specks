package com.sandeepmassey.specks.imaging.util

/**
 * Created by Sandeep Massey on 10-05-2022
 */
sealed class GalleryUIAction {
    object OnShareClick : GalleryUIAction()
    object OnDeleteClick : GalleryUIAction()
    object OnBackClick : GalleryUIAction()
}
