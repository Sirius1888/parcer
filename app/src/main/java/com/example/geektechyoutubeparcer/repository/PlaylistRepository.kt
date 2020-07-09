package com.example.geektechyoutubeparcer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.network.RetrofitClient
import com.example.geektechyoutubeparcer.network.YoutubeAPi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistRepository() {

    val channelId = "UCuVpbMt4yu4I9eAey3khnGg"
    val apiKey = "AIzaSyCYF-XD4f4Jr0xkwJfxzAiV6rW4k_JUvDI"
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

    fun fetchYoutubePlaylistById(id: String): MutableLiveData<Playlist> {
        apiService = RetrofitClient.create()
        val data = MutableLiveData<Playlist>()
        apiService?.getSelectedPlaylist(part, apiKey, id, maxResult)?.enqueue(object :
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