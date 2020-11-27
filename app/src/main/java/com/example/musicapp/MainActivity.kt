package com.example.musicapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.db.SongsRepository
import com.example.musicapp.preferences.PreferenceActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),
    SongsListener {

    private lateinit var rvSongs: RecyclerView
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var fabAdd: FloatingActionButton
    private val adapter: SongAdapter by lazy {
        SongAdapter(
            this
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        retrieveSongs()
    }

    private fun setupUI() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        fabAdd = findViewById(R.id.floatingActionButton)
        fabAdd.setOnClickListener { launchAddSongActivity() }




        rvSongs = findViewById(R.id.rvSongs)
        rvSongs.adapter = adapter
    }

    private fun launchAddSongActivity() {
        startActivity(
            Intent(this, AddSongActivity::class.java)
        )
    }

    private fun retrieveSongs() {
        val songs = SongsRepository(this@MainActivity.applicationContext).getSongs()
        //val games = GamesProvider.getGames()
        adapter.updateSong(songs)
    }

    override fun onSongClicked(song: Song) {
        val intent = Intent()
        intent.putExtra("Song", song)

        /*
        Snackbar
            .make(coordinatorLayout, "work in progres", Snackbar.LENGTH_LONG)
            .show()

         */

        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(song.name)
            .setMessage("${song.artist}")
            .setPositiveButton("DETALLE", { _, _ ->
                Snackbar.make(
                    coordinatorLayout, "In progress", Snackbar.LENGTH_LONG
                )
            })
            .setNeutralButton("MODIFICAR", { _, _ ->
                launchEditSongActivity(song.id)
            })
            .setNegativeButton("ELIMINAR", { _, _ ->
                deleteSong(song)

            })
            .setCancelable(false)
            .show()
    }

    private fun launchEditSongActivity(id: Int?) {
        //Ir hacia otra activity
        val intent = Intent(this, AddSongActivity::class.java)
        //metodo para enviar parametros
        intent.putExtra("IDSONG", id)
        //Metodo para comenzar la Activity
        startActivity(intent)
    }

    private fun deleteSong(song: Song) {
            SongsRepository(this@MainActivity.applicationContext).deleteSong(song)
            showMessage("Canción Eliminada")
            retrieveSongs()

    }

    private fun showMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()


}
