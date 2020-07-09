package com.example.geektechyoutubeparcer.ui.playlist

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.base.BaseActivity
import com.example.geektechyoutubeparcer.model.PlaylistItem
import com.example.geektechyoutubeparcer.ui.detail_playlist.DetailPlaylistActivity
import com.example.geektechyoutubeparcer.ui.playlist.adapter.PlaylistAdapter
import kotlinx.android.synthetic.main.activity_main.*

class PlaylistActivity : BaseActivity(R.layout.activity_main), PlaylistAdapter.Listener {

    private var adapter: PlaylistAdapter? = PlaylistAdapter(this)
    private var viewModel: PlaylistViewModel? = null

    override fun setupUI() {
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
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
