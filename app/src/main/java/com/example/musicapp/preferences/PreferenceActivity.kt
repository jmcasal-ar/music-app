package com.example.musicapp.preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.R

class PreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        showPreferencesFragment()
    }

    private fun showPreferencesFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, PreferenceFragment())
            .commit()
    }
}