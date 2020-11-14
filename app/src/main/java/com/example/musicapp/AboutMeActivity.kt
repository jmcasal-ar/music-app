package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class AboutMeActivity : AppCompatActivity() {
    private lateinit var  tvWelcome: TextView
    private lateinit var  tvDescriptionAboutMe: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        setupUI()
    }

    private fun setupUI() {
        tvWelcome = findViewById(R.id.tvWelcome)
        tvDescriptionAboutMe = findViewById(R.id.tvDescriptionAboutMe)
    }
}