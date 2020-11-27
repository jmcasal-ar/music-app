package com.example.musicapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.example.musicapp.preferences.PreferenceActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var  tvWelcome: TextView
    private lateinit var  tvUser: TextView
    private lateinit var btnIngresar: Button
    private lateinit var btnAboutMe: Button
    private lateinit var btnDonate: Button
    private val preferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_werlcome)

        setupUI()
    }



    private fun setupUI() {
        tvWelcome = findViewById(R.id.tvWelcome)
        tvUser = findViewById(R.id.tvUser)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnAboutMe = findViewById(R.id.btnAboutMe)
        btnDonate = findViewById(R.id.btnDonar)

        btnAboutMe.setOnClickListener { ingresarAboutMe() }

        btnDonate.setOnClickListener { ingresarDonate() }

        btnIngresar.setOnClickListener { ingresarApp() }


    }



    override fun onResume() {
        super.onResume()
        handleUserTextVisibility()

    }

    private fun handleUserTextVisibility() {
        val nameUser = preferences.getString("etPreferenceName", "")
        tvUser.text = nameUser
        tvUser.visibility

    }

    private fun ingresarAboutMe() {
        val intent = Intent(this, AboutMeActivity::class.java)
        startActivity(intent)
    }

    private fun ingresarApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun ingresarDonate() {
        val intent = Intent(this, DonateActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemSettings) {
            launchSettings()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun launchSettings() {
        startActivity(
            Intent(this, PreferenceActivity::class.java)
        )
    }


}