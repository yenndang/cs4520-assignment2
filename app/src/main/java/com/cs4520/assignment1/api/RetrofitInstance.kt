package com.cs4520.assignment1.api

import com.cs4520.assignment1.api.ApiService
import com.cs4520.assignment4.com.cs4520.assignment1.Api
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.cs4520.assignment1.models.Product

object RetrofitInstance {
    private val retrofit by lazy {
        val gson = GsonBuilder()
            .registerTypeAdapter(Product::class.java, ProductDeserializer())
            .create()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

