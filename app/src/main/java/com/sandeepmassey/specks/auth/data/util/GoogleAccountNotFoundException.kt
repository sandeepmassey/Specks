package com.sandeepmassey.specks.auth.data.util

/**
 * Created by Sandeep Massey on 18-03-2022
 */
class GoogleAccountNotFoundException(
    override val message: String? = "Google Account Not Found."
) : Exception()