package com.example.geektechyoutubeparcer.network

import com.example.geektechyoutubeparcer.model.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeAPi {

    @GET("v3/playlists")
    fun fetchAllPlaylists(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("maxResult") maxResult: String
    ): Call<Playlist>

    @GET("v3/playlistItems")
    fun getSelectedPlaylist(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("playlistId") playlistId: String?,
        @Query("maxResults") maxResult: String
    ): Call<Playlist>

    @GET("v3/videos")
    fun getDetailVideo(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("id") id: String
    ): Call<Playlist>

}