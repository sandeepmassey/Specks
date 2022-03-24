package com.sandeepmassey.specks.core.util

/**
 * Created by Sandeep Massey on 18-03-2022
 */
sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val t: Throwable) : RequestState<Nothing>()
}
