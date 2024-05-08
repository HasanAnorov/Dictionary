package com.ierusalem.dictionary.core.app

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.ierusalem.dictionary.core.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance(context: Context) {

    private val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
    )

    // Create the Interceptor
    private val chuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(chuckerCollector)
        .maxContentLength(250_000L)
        .alwaysReadResponseBody(false)
        .redactHeaders(emptySet())
        .build()

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(chuckerInterceptor)
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

}
