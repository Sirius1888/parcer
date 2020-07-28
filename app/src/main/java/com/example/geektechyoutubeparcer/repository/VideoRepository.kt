package com.example.geektechyoutubeparcer.repository

import androidx.lifecycle.MutableLiveData
import com.example.geektechyoutubeparcer.Constants.apiKey
import com.example.geektechyoutubeparcer.Constants.part
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.network.RetrofitClient
import com.example.geektechyoutubeparcer.network.YoutubeAPi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoRepository(private var service: YoutubeAPi) {

    fun fetchVideoById(videoId: String): MutableLiveData<Playlist> {
        val data = MutableLiveData<Playlist>()
        service.getDetailVideo(apiKey, part, videoId).enqueue(object :
            Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                data.value = response.body()
            }
        })
        return data
    }

}