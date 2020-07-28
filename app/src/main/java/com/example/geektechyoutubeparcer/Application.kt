package com.example.geektechyoutubeparcer

import android.app.Application
import com.example.geektechyoutubeparcer.di.networkModule
import com.example.geektechyoutubeparcer.di.repositoryModule
import com.example.geektechyoutubeparcer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(listOf(viewModelModule, repositoryModule, networkModule))
        }
    }
}