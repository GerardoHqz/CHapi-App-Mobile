package com.tde.chapi

import android.app.Application
import com.tde.chapi.viewmodel.Prefs

class CHapiApplication : Application() {

    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }

}