package com.sandeepmassey.specks.auth.data.util

/**
 * Created by Sandeep Massey on 18-03-2022
 */
class EmptyFieldException(
    override val message: String = "Empty Input Field."
) : Exception()