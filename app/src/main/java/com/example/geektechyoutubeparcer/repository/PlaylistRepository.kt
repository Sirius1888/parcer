package com.example.geektechyoutubeparcer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geektechyoutubeparcer.Constants.apiKey
import com.example.geektechyoutubeparcer.Constants.channelId
import com.example.geektechyoutubeparcer.Constants.maxResult
import com.example.geektechyoutubeparcer.Constants.part
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.network.YoutubeAPi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistRepository(private var apiService: YoutubeAPi?) {

    fun fetchYoutubePlaylist(): LiveData<Playlist?> {
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