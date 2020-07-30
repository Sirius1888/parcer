package com.example.geektechyoutubeparcer.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.extension.loadImage
import com.example.geektechyoutubeparcer.model.PlaylistItem
import com.example.geektechyoutubeparcer.utils.YoutubeVideo
import kotlinx.android.synthetic.main.item_download.view.*
import kotlinx.android.synthetic.main.item_playlist.view.*

class DownloadAdapter(private val listener: Listener) : RecyclerView.Adapter<DownloadAdapter.ViewHolder>() {

    var list = mutableListOf<YoutubeVideo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_download, parent, false)
        return ViewHolder(view, parent.context)
    }

    fun addItems(list: MutableList<YoutubeVideo>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun deselectItems() {
        for (i in 0 until list.size) {
            if (list[i].isSelected) list[i].isSelected = false
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.radio.isChecked = list[position].isSelected
        holder.radio.text = "${item.height} p"
        holder.radio.setOnClickListener {
            deselectItems()
            list[position].isSelected = true
            listener.onItemClick(list[position])
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
        var radio: RadioButton = itemView.findViewById(R.id.radio)

    }

    interface Listener {
        fun onItemClick(dto: YoutubeVideo)
    }
}