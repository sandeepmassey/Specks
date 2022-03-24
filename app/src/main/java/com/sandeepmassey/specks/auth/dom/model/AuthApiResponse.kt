package com.sandeepmassey.specks.auth.dom.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@Serializable
data class AuthApiResponse(
    val success: Boolean,
    val user: User? = null,
    val message: String? = null,
    @Transient
    val error: Exception? = null
)
