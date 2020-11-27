package com.example.musicapp.db

import android.content.Context
import com.example.musicapp.Song
import com.example.musicapp.db.DBHelper
import com.j256.ormlite.android.apptools.OpenHelperManager
import com.j256.ormlite.dao.Dao

class SongsRepository(context: Context) {

    private var dao: Dao<Song, Int>

    init {
        val helper = OpenHelperManager.getHelper(context, DBHelper::class.java)
        dao = helper.getDao(Song::class.java)
    }

    fun addSong(song: Song) = dao.create(song)

    fun deleteSong(song: Song) = dao.delete(song)

    fun updateSong(song: Song) = dao.update(song)

    fun getSongs() = dao.queryForAll()

    fun getSong(songId: Int) = dao.queryForId(songId)

}