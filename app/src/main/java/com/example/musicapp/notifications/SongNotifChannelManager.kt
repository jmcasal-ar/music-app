package com.example.musicapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat

object SongNotifChannelManager {

    const val NEW_SONGS_CHANNEL_ID = "1"
    private const val NEW_SONGS_CHANNEL_NAME = "Nuevas canciones"
    private const val NEW_SONGS_CHANNEL_DESCRIPTION = "Serás notificado al agregar nuevas canciones."

    fun createNotificationChannelForNewSongs(context: Context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = NotificationManagerCompat.from(context)

            val channel = NotificationChannel(
                NEW_SONGS_CHANNEL_ID,
                NEW_SONGS_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = NEW_SONGS_CHANNEL_DESCRIPTION
            notificationManager.createNotificationChannel(channel)
        }
    }
}



