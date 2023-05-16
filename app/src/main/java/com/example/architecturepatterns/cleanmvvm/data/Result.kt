package com.example.architecturepatterns.cleanmvvm.data

sealed class Result<out T : Any>
class Success<out T : Any>(val data: T) : Result<T>()
class Failure(val message: String) : Result<Nothing>()
