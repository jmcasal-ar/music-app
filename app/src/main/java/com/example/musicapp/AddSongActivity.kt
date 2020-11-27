package com.example.musicapp

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.musicapp.db.SongsRepository
import com.example.musicapp.notifications.SongNotifChannelManager
import com.google.android.material.textfield.TextInputEditText

class AddSongActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etArtist: TextInputEditText
    private lateinit var cbSpotify: CheckBox
    private lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)
        setupUI()
    }

    private fun setupUI() {
        etName = findViewById(R.id.etName)
        etArtist = findViewById(R.id.etArtist)
        cbSpotify = findViewById(R.id.cbSpotify)
        btnSave = findViewById(R.id.btnSave)
        editSong()
        btnSave.setOnClickListener { saveGame() }
    }

    private fun editSong() {
        val bundle = intent.extras
        val idSong = bundle?.getInt("IDSONG") ?: 0
        if (idSong > 0){
            fillDataSong(idSong)
        }
    }

    private fun fillDataSong(idSong: Int) {
        val song = SongsRepository(this@AddSongActivity.applicationContext)
            .getSong(idSong)
        showMessage("Canción a Editar")
        etName.setText(song.name)
        etArtist.setText(song.artist)
        cbSpotify.isChecked = song.spotify
        //SongsRepository(this@AddSongActivity.applicationContext)
            //.updateSong(createSongFromEdit(song))

    }




    private fun saveGame() {
        validateData()
        if (isDataValid()) {
            editOrAddSong()

        }
    }

    private fun editOrAddSong() {
        val bundle = intent.extras
        val idSong = bundle?.getInt("IDSONG") ?: 0
        if (idSong > 0) {
            val song = SongsRepository(this@AddSongActivity.applicationContext)
                .getSong(idSong)
            SongsRepository(this@AddSongActivity.applicationContext)
                .updateSong(editSongFromInput(song))
            showMessage("Canción Editada")
            finish()
        } else {
            SongsRepository(this@AddSongActivity.applicationContext)
                .addSong(createSongFromInput())
            showMessage("Canción Agregada")
            showNotificacionts()
            finish()
        }
    }

    private fun showNotificacionts() {
        SongNotifChannelManager.createNotificationChannelForNewSongs(this)

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        NotificationCompat.Builder(this, SongNotifChannelManager.NEW_SONGS_CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Nueva canción agregada")
            .setContentText("Click para ver tu repositorio actualizado")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
            .also {
                    notification ->
                NotificationManagerCompat
                    .from(this)
                    .notify(1, notification)
            }
    }

    private fun editSongFromInput(song: Song): Song {

        song.name = getTextFrom(etName)
        song.artist = getTextFrom(etArtist)
        song.spotify = getcbSpotify()

        return song
    }



    private fun validateData() {
        validateName()
        validateArtist()
    }

    private fun validateArtist() {
        if (getTextFrom(etArtist).isEmpty()) {
            etName.error = "Completar artista"
        }
    }

    private fun validateName() {
        if (getTextFrom(etName).isEmpty()) {
            etName.error = "Completar nombre"
        }
    }

    private fun isDataValid() =
        etName.error.isNullOrEmpty() && etArtist.error.isNullOrEmpty()

    private fun createSongFromInput(): Song {
        return Song(
            getTextFrom(etName),
            getTextFrom(etArtist),
            getcbSpotify()

        )
    }

    private fun getcbSpotify(): Boolean {
        return if (cbSpotify.isChecked) {
            true
        } else {
            return false
        }
    }

    private fun showMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()


    private fun getTextFrom(editText: EditText) = editText.text.toString()
}

