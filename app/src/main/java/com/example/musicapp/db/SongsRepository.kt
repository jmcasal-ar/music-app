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

    fun addSong(game: Song) = dao.create(game)

    fun deleteSong(game: Song) = dao.delete(game)

    fun updateSong(game: Song) = dao.update(game)

    fun getSongs() = dao.queryForAll()

    fun getSong(gameId: Int) = dao.queryForId(gameId)

}