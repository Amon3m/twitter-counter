package com.semicolon.domain.util

sealed class Response<T>(
    val data : T? = null,
    val status : String? = null,
    val message : String? = null,
    val error : String? = null

){
    class Success<T> (data : T) : Response<T>(data = data)
    class Failure<T> (error : String) : Response<T>(error = error)
}