package com.example.myapplication.domain


sealed class UIResult<out T> {

    object Loading :  UIResult<Nothing>()
    data class Success<T>(val data: T) : UIResult<T>()
    data class Failure(val error: String) : UIResult<Nothing>()
}