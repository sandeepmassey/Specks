package com.sandeepmassey.specks.recipes.data.local

import androidx.room.TypeConverter
import com.sandeepmassey.specks.recipes.dom.model.Mixing
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by Sandeep Massey on 21-03-2022
 */
class DatabaseConverter {

    private val separator = ","

    private var json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(separator)
        }

        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }

    @TypeConverter
    fun mixingListToString(list: List<Mixing>): String {
        return json.encodeToString(list)
    }

    @TypeConverter
    fun stringToMixingList(data: String): List<Mixing> {
        return json.decodeFromString(data)
    }

    @TypeConverter
    fun parametersListToString(list: List<Map<String, String>>): String {
        return json.encodeToString(list)
    }

    @TypeConverter
    fun stringToParametersList(data: String): List<Map<String, String>> {
        return json.decodeFromString(data)
    }

}