package com.example.musicapp.preferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.musicapp.R

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}