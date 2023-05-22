package com.example.architecturepatterns.cleanmvvm.common

import com.example.architecturepatterns.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

val loggingInterceptor = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
}
