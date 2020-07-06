package com.example.geektechyoutubeparcer.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient() {

    companion object {

        private const val BASE_URL = "https://www.googleapis.com/youtube/"

        val httpLogging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        fun create(): YoutubeAPi? {
            val okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(httpLogging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()

            return retrofit.create(YoutubeAPi::class.java)

        }

    }

}