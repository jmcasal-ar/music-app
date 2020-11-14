package com.example.musicapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
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
            .setMessage("Esta canción es de  ${song.artist}")
            .setPositiveButton("DETALLE", { _, _ ->
                Snackbar.make(
                    coordinatorLayout, "In progress", Snackbar.LENGTH_LONG
                )
            })
            .setNeutralButton("MODIFICAR", { _, _ ->

            })
            .setNegativeButton("ELIMINAR", { _, _ ->

            })
            .setCancelable(false)
            .show()
    }


}
