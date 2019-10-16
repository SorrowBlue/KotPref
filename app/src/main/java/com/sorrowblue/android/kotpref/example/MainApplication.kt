package com.sorrowblue.android.kotpref.example

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

@Suppress("unused")
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}
