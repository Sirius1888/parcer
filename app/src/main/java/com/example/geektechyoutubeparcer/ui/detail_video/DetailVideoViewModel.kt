package com.example.geektechyoutubeparcer.ui.detail_video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.repository.PlaylistRepository
import com.example.geektechyoutubeparcer.repository.VideoRepository

class DetailVideoViewModel : ViewModel() {

    fun fetchVideoById(relatedToVideoId: String): LiveData<Playlist?> {
        return VideoRepository().fetchVideoById(relatedToVideoId)
    }
}