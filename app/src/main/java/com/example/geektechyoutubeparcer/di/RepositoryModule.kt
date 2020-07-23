package com.example.geektechyoutubeparcer.di

import com.example.geektechyoutubeparcer.repository.DetailPlaylistRepository
import com.example.geektechyoutubeparcer.repository.PlaylistRepository
import com.example.geektechyoutubeparcer.repository.VideoRepository
import org.koin.dsl.module


var repositoryModule = module {
    factory { DetailPlaylistRepository(get()) }
    factory { PlaylistRepository(get()) }
    factory { VideoRepository() }
    // factory { }
    // single { }

}