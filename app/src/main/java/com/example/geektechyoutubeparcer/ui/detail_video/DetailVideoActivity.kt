package com.example.geektechyoutubeparcer.ui.detail_video

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.SparseArray
import androidx.lifecycle.Observer
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.base.BaseActivity
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.model.PlaylistItem
import com.example.geektechyoutubeparcer.utils.CallBacks
import com.example.geektechyoutubeparcer.utils.PlayerManager
import com.example.geektechyoutubeparcer.utils.YoutubeVideo
import com.google.android.exoplayer2.Player
import kotlinx.android.synthetic.main.activity_video.*
import org.koin.android.ext.android.inject

class DetailVideoActivity : BaseActivity(R.layout.activity_video), CallBacks {

    private val viewModel by inject<DetailVideoViewModel>()

    private lateinit var player: Player
    private lateinit var playerManager: PlayerManager
    private var listOfFormatVideo = mutableListOf<YoutubeVideo>()
    override fun setupUI() {
        setupExoPlayer()
        setupToolbar()
    }

    private fun setupExoPlayer() {
        playerManager = PlayerManager.getSharedInstance(this)
        player = playerManager.playerView.player
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun setupLiveData() {
        subscribeToVideo()

    }

    private fun subscribeToVideo() {
        videoId?.let {
            viewModel.fetchVideoById(it).observe(this, Observer { playlist ->
                setupViews(getNeededVideo(playlist))
            })
        }
    }

    private fun getNeededVideo(dto: Playlist?): PlaylistItem {
        if (dto == null) return PlaylistItem()

        for (i in dto.items) {
            if (i.id == videoId) {
                return i
            }
        }
        return PlaylistItem()
    }

    private fun setupViews(dto: PlaylistItem) {
        title_video.text = dto.snippet?.title
        video_sub_title.text = dto.snippet?.description
        getActualUrl(dto.id)
    }

    @SuppressLint("StaticFieldLeak")
    private fun getActualUrl(url: String?) {
        object : YouTubeExtractor(this) {
            public override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, videoMeta: VideoMeta?) {
                var i = 0
                var itag: Int
                if (ytFiles != null) {
                    while (i < ytFiles.size()) {
                        itag = ytFiles.keyAt(i)
                        var ytFile = ytFiles.get(itag)

                        if (ytFile.format.height == -1 || ytFile.format.height >= 360) {
                            addFormatToList(ytFile, ytFiles)
                        }
                        i++
                    }
                }
                val videoUrl: YoutubeVideo? = listOfFormatVideo.last()
                playVideo(videoUrl?.videoFile?.url)

            }
        }.extract(url, true, true)
    }

    private fun addFormatToList(ytFile: YtFile, ytFiles: SparseArray<YtFile>) {
        var height = ytFile.format.height
        if (height != -1) {
            for (video in listOfFormatVideo) {
                if (video?.height == height && (video.videoFile == null || video.videoFile!!.format.fps == ytFile.format.fps)) {
                    return
                }
            }

            val yVideo = YoutubeVideo()
            yVideo.height = height
            if (ytFile.format.isDashContainer) {
                if (height > 0) {
                    yVideo.videoFile = ytFile
                    yVideo.audioFile = ytFiles.get(140)
                } else {
                    yVideo.audioFile = ytFile
                }
            }
            listOfFormatVideo.add(yVideo)
        }
    }

    private fun playVideo(url: String?) {
        player_view?.player = player
        PlayerManager.getSharedInstance(this).playStream(url)
        PlayerManager.getSharedInstance(this).setPlayerListener(this)
    }

    companion object {
        private var videoId: String? = null
        fun instance(activity: Activity?, videoId: String?) {
            val intent = Intent(activity, DetailVideoActivity::class.java)
            activity?.startActivity(intent)
            this.videoId = videoId
        }
    }

    override fun onItemClickOnItem(albumId: Int) {

    }

    override fun onPlayingEnd() {

    }

}