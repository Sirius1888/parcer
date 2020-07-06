package com.example.geektechyoutubeparcer.ui.detail_playlist

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.extension.showToast

class DetailPlaylistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_playlist)
        showToast(id.toString())
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
