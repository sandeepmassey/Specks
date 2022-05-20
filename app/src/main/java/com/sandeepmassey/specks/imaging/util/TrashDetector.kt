package com.sandeepmassey.specks.imaging.util

import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import org.opencv.photo.Photo

/**
 * Created by Sandeep Massey on 14-05-2022
 */
class TrashDetector {

    private val mContours: ArrayList<MatOfPoint> = ArrayList()

    // Cache
    private var matHSV = Mat()
    private var matChannels = mutableListOf<Mat>()
    private var matBlur = Mat()
    private var matDenoised = Mat()
    private var matBinary = Mat()
    private var matEroded = Mat()
    private var matDilated = Mat()
    private var matHierarchy = Mat()

    fun processedRGBAImage(rgbaImage: Mat) {
        val total = rgbaImage.rows() * rgbaImage.cols()
        Imgproc.cvtColor(rgbaImage, matHSV, Imgproc.COLOR_BGR2HSV)
        Core.split(matHSV, matChannels)
        Imgproc.blur(matChannels[2], matBlur, Size(15.0, 15.0))
        Photo.fastNlMeansDenoising(matBlur, matDenoised, 10f, 7, 21)
        Imgproc.threshold(
            matDenoised,
            matBinary,
            127.0,
            255.0,
            Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU
        )
        val kernel = Mat.ones(Size(5.0, 5.0), CvType.CV_8U)
        Imgproc.erode(matBinary, matEroded, kernel, Point(-1.0, -1.0), 1)
        Imgproc.dilate(matEroded, matDilated, kernel, Point(-1.0, -1.0), 1)

        Imgproc.findContours(
            matDilated,
            mContours,
            matHierarchy,
            Imgproc.RETR_TREE,
            Imgproc.CHAIN_APPROX_SIMPLE
        )
        for (i in mContours) {
            if (Imgproc.contourArea(i) < total) {
                Imgproc.drawContours(
                    rgbaImage,
                    mContours,
                    -1,
                    Scalar(0.0, 255.0, 0.0),
                    3
                )
            }
        }
        // Clean up
        matHSV.release()
        matChannels.forEach { it.release() }
        matBlur.release()
        matDenoised.release()
        matBinary.release()
        matEroded.release()
        matDilated.release()
        matHierarchy.release()
    }
}