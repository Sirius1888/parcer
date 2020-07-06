package com.example.geektechyoutubeparcer.ui.playlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.PlaylistItem
import com.example.geektechyoutubeparcer.ui.detail_playlist.DetailPlaylistActivity
import com.example.geektechyoutubeparcer.ui.playlist.adapter.PlaylistAdapter
import kotlinx.android.synthetic.main.activity_main.*

class PlaylistActivity : AppCompatActivity(), PlaylistAdapter.Listener {

    private var viewModel: PlaylistViewModel? = null
    private var adapter: PlaylistAdapter? = PlaylistAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        setupAdapter()
        setupToSubscribe()
    }

    private fun setupAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {
                adapter?.addItems(it?.items!!)
            }
        })
    }

    override fun onItemClick(dto: PlaylistItem) {
        DetailPlaylistActivity.instance(this, dto.id)
    }

    //создать свой апи кей
    //добавить в класс playlist поле "items"
    //отрисовать всё в адаптере
    //сделать переход на новую активити и передаете туда id и её отображаете тостом

}
