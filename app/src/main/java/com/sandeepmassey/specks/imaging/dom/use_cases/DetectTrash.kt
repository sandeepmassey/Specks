package com.sandeepmassey.specks.imaging.dom.use_cases

import com.sandeepmassey.specks.imaging.util.TrashDetector

/**
 * Created by Sandeep Massey on 14-05-2022
 */
class DetectTrash {
    operator fun invoke() = TrashDetector()
}