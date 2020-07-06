package com.example.geektechyoutubeparcer.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.network.RetrofitClient
import com.example.geektechyoutubeparcer.network.YoutubeAPi
import com.example.geektechyoutubeparcer.repository.PlaylistRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : ViewModel() {

    fun fetchPlaylist(): LiveData<Playlist?> {
        return PlaylistRepository().fetchYoutubePlaylist()
    }

}