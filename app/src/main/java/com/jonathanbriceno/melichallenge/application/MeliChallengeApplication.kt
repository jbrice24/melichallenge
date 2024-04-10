package com.jonathanbriceno.melichallenge.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MeliChallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}