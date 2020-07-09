package com.example.geektechyoutubeparcer.ui.detail_playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.repository.PlaylistRepository

class DetailPlaylistViewModel : ViewModel() {

    fun fetchDetailPlaylist(id: String): MutableLiveData<Playlist> {
        return PlaylistRepository().fetchYoutubePlaylistById(id)
    }
}