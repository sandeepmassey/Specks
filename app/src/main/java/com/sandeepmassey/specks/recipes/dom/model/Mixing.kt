package com.sandeepmassey.specks.recipes.dom.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Sandeep Massey on 24-03-2022
 */
@Serializable
class Mixing(
    @SerialName("Material")
    val material: String,
    @SerialName("Tint")
    val tint: String,
    @SerialName("Length")
    val length: String,
    @SerialName("Count")
    val count: String,
    @SerialName("Shape")
    val shape: String,
    @SerialName("Ratio")
    val ratio: String,
    @SerialName("Type")
    val type: String
)