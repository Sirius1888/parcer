package com.example.geektechyoutubeparcer.ui.detail_video

import android.app.Activity
import android.content.Intent
import android.text.method.ScrollingMovementMethod
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video.*

class DetailVideoActivity : BaseActivity(R.layout.activity_video) {
    private var viewModel: DetailVideoViewModel? = null

    override fun setupUI() {
        viewModel = ViewModelProviders.of(this).get(DetailVideoViewModel::class.java)
    }

    override fun setupLiveData() {
        subscribeToDetailPlaylist()
    }

    private fun subscribeToDetailPlaylist() {
        videoId?.let {
            viewModel?.fetchVideoById(it)?.observe(this, Observer {
                if (it != null) {
                    for (i in it.items) {
                        if (i.id == videoId) {
                            title_video.text = i.snippet!!.title
                            video_sub_title.movementMethod = ScrollingMovementMethod()
                            video_sub_title.text = i.snippet.description
                        }
                    }
                }
            })
        }
    }

    companion object {
        private var videoId: String? = null
        private var position: Int? = null
        fun instance(activity: Activity?, videoId: String?) {
            val intent = Intent(activity, DetailVideoActivity::class.java)
            activity?.startActivity(intent)
            this.videoId = videoId
            this.position = position
        }
    }

}