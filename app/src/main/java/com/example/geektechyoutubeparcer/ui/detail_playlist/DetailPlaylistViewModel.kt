package com.example.geektechyoutubeparcer.ui.detail_playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.repository.DetailPlaylistRepository

class DetailPlaylistViewModel(private val detailPlaylistRepository: DetailPlaylistRepository) : ViewModel() {

    fun fetchDetailPlaylist(id: String): MutableLiveData<Playlist> {
        return detailPlaylistRepository.fetchYoutubePlaylistById(id)
    }

}