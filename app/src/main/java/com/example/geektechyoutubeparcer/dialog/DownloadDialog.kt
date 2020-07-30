package com.example.geektechyoutubeparcer.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.dialog.adapter.DownloadAdapter
import com.example.geektechyoutubeparcer.utils.YoutubeVideo
import kotlinx.android.synthetic.main.dialog_download.*

class DownloadDialog(
    context: Context,
    private var listener: DownloadDialog.Listener,
    private var listOfFormatVideo: MutableList<YoutubeVideo>
) : Dialog(context, R.style.DialogStyle),
    DownloadAdapter.Listener {

    val adapter = DownloadAdapter(this)
    var selectedItem: YoutubeVideo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_download)
        setupAdapter()
        downloadAction()
    }

    private fun setupAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        adapter.addItems(listOfFormatVideo)
    }

    override fun onItemClick(dto: YoutubeVideo) {
        selectedItem = dto
    }

    private fun downloadAction() {
        download.setOnClickListener {
            listener.downloadedItem(selectedItem)
            dismiss()
        }
    }

    interface Listener {
        fun downloadedItem(dto: YoutubeVideo?)
    }

}