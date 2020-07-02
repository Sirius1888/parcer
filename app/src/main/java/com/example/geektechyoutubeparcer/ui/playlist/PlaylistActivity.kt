package com.example.geektechyoutubeparcer.ui.playlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.geektechyoutubeparcer.R

class PlaylistActivity : AppCompatActivity() {

    private var viewModel: PlaylistViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)

        setupToSubscribe()
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        })
    }

    //создать свой апи кей
    //добавить в класс playlist поле "items"
    //отрисовать всё в адаптере
    //сделать переход на новую активити и передаете туда id и её отображаете тостом

}
