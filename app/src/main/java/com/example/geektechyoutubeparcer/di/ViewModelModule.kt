package com.example.geektechyoutubeparcer.di

import com.example.geektechyoutubeparcer.ui.detail_playlist.DetailPlaylistViewModel
import com.example.geektechyoutubeparcer.ui.detail_video.DetailVideoViewModel
import com.example.geektechyoutubeparcer.ui.playlist.PlaylistViewModel
import org.koin.dsl.module

var viewModelModule = module {
    factory { PlaylistViewModel(get()) }
    factory { DetailPlaylistViewModel(get()) }
    factory { DetailVideoViewModel() }
}