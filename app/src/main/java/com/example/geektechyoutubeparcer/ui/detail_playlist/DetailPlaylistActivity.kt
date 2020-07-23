package com.example.geektechyoutubeparcer.ui.detail_playlist

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.Shared
import com.example.geektechyoutubeparcer.base.BaseActivity
import com.example.geektechyoutubeparcer.extension.showToast
import com.example.geektechyoutubeparcer.model.PlaylistItem
import com.example.geektechyoutubeparcer.ui.detail_video.DetailVideoActivity
import com.example.geektechyoutubeparcer.ui.playlist.PlaylistViewModel
import com.example.geektechyoutubeparcer.ui.playlist.adapter.PlaylistAdapter
import kotlinx.android.synthetic.main.activity_main.*

class DetailPlaylistActivity : BaseActivity(R.layout.activity_detail_playlist), PlaylistAdapter.Listener {

    private lateinit var viewModel: DetailPlaylistViewModel
    private var adapter: PlaylistAdapter? = PlaylistAdapter(this)

    override fun setupUI() {
        viewModel = ViewModelProviders.of(this).get(DetailPlaylistViewModel::class.java)
        setupAdapter()
    }

    override fun onItemClick(dto: PlaylistItem) {
         DetailVideoActivity.instance(this, dto.snippet?.resourceId?.videoId)
    }

    // сделать запрос на получение информации о видео, всё по дизаину.
    // доп - сделать проверку на интернет соединение.

    private fun <T> count(a: T, b: T) {
        var message = "$a & $b"
        showToast(message)
    }

    private fun setupAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.adapter = adapter
    }

    override fun setupLiveData() {
        subscribeToDetailPlaylist()
    }

    private fun subscribeToDetailPlaylist() {
        id?.let {
            viewModel.fetchDetailPlaylist(it).observe(this, Observer {
                adapter?.addItems(it?.items!!)
            })
        }
    }

    companion object {
        private var id: String? = null
        fun instance(activity: Activity?, id: String?) {
            val intent = Intent(activity, DetailPlaylistActivity::class.java)
            activity?.startActivity(intent)
            this.id = id
        }
    }
}
