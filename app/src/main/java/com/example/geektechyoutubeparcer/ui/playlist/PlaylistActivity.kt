package com.example.geektechyoutubeparcer.ui.playlist

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.base.BaseActivity
import com.example.geektechyoutubeparcer.model.PlaylistItem
import com.example.geektechyoutubeparcer.ui.detail_playlist.DetailPlaylistActivity
import com.example.geektechyoutubeparcer.ui.playlist.adapter.PlaylistAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class PlaylistActivity : BaseActivity(R.layout.activity_main), PlaylistAdapter.Listener {

    private var adapter: PlaylistAdapter? = PlaylistAdapter(this)
    private val viewModel by inject<PlaylistViewModel>()

    override fun setupUI() {
        setupAdapter()
    }

    private fun setupAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.adapter = adapter
    }

    override fun onItemClick(dto: PlaylistItem) {
        DetailPlaylistActivity.instance(this, dto.id)
    }

    override fun setupLiveData() {
        setupToSubscribe()
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {
                adapter?.addItems(it?.items!!)
            }
        })
    }
}
