package com.example.geektechyoutubeparcer.di

import com.example.geektechyoutubeparcer.Shared
import com.example.geektechyoutubeparcer.network.RetrofitClient
import com.example.geektechyoutubeparcer.network.YoutubeAPi
import com.example.geektechyoutubeparcer.network.provideLoggingInterceptor
import com.example.geektechyoutubeparcer.network.provideOkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

var networkModule = module {
    single { Shared(androidApplication()) }
    single { RetrofitClient(get(), get()) }
    single { provideOkHttpClient(get()) }
    single { provideLoggingInterceptor() }
    factory { provideYoutubeApi(get()) }
}
//ExoPlayer
fun provideYoutubeApi(retrofit: RetrofitClient): YoutubeAPi = retrofit.provideRetrofit().create(YoutubeAPi::class.java)