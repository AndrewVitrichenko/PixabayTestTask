package com.pixabay.testtask.util

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Throwable>()
    data class Message(val message: String) : Result<String>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Throwable[throwable=$throwable]"
            is Message -> "Message[message=$message]"
            Loading -> "Loading"
        }
    }
}

fun <T> Result<T>.getData(): T {
    return (this as Result.Success<T>).data
}

fun Result<String>.getMessage() : String{
    return (this as Result.Message).message
}

fun Result<Throwable>.getThrowable(): Throwable {
    return (this as Result.Error).throwable
}
