package com.example

import android.app.Application
import com.example.geektechyoutubeparcer.di.networkModule
import com.example.geektechyoutubeparcer.di.repositoryModule
import com.example.geektechyoutubeparcer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Parcer : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Parcer)
            modules(listOf(viewModelModule, repositoryModule, networkModule))
        }
    }
}