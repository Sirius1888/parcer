package com.example.geektechyoutubeparcer.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.network.RetrofitClient
import com.example.geektechyoutubeparcer.network.YoutubeAPi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : ViewModel() {

    fun fetchPlaylist(): LiveData<Playlist?> {
        return fetchYoutubePlaylist()
    }

    val channelId = "UC_IfiZu3VkesO3L58L9WPhA"
    val apiKey = "AIzaSyCWK-EoCHecYMMFAvl-DI5iegR9s1WW20Y"
    val part = "snippet,contentDetails"
    val maxResult = "50"

    private  var apiService: YoutubeAPi? = null
    fun fetchYoutubePlaylist(): LiveData<Playlist?> {
        apiService = RetrofitClient.create()
        val data = MutableLiveData<Playlist?>()
        apiService?.fetchAllPlaylists(part, apiKey, channelId, maxResult)?.enqueue(object :
            Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                //500.. и выше
                data.value = null
            }

            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()
            }

        })
        return data
    }
}