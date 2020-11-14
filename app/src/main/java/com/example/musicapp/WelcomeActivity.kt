package com.example.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    private lateinit var  tvWelcome: TextView
    private lateinit var btnIngresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_werlcome)

        setupUI()
    }

    private fun setupUI() {
        tvWelcome = findViewById(R.id.tvWelcome)
        btnIngresar = findViewById(R.id.btnIngresar)

        btnIngresar.setOnClickListener { ingresarApp() }
    }

    private fun ingresarApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}