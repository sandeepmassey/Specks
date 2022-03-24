package com.sandeepmassey.specks.recipes.dom.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Sandeep Massey on 24-03-2022
 */
@Serializable
class Mixing(
    @SerialName("mat")
    val mat: String,
    @SerialName("len")
    val len: String,
)