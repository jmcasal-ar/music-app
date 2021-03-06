package com.example.musicapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(
    val listener: SongsListener
) : RecyclerView.Adapter<SongAdapter.SongsViewHolder>() {

    private var songs: List<Song> = emptyList()

    class SongsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtArtist: TextView = itemView.findViewById(R.id.txtArtist)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_song,
                parent,
                false
            )

        return SongsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.apply {
            txtName.text = songs[position].name
            txtArtist.text = songs[position].artist
            /*aca va el resto de los campos*/

            itemView.setOnClickListener {
                listener.onSongClicked(songs[position])
            }
        }
    }

    override fun getItemCount() = songs.size

    fun updateSong(songs: List<Song>) {
        this.songs = songs
        notifyDataSetChanged()
    }
}

interface SongsListener {
    fun onSongClicked(song: Song)
}