package com.sandeepmassey.specks.auth.dom.model

import kotlinx.serialization.Serializable

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@Serializable
data class User(
    val id: String,
    val name: String,
    val emailAddress: String,
    val profilePhoto: String
)
