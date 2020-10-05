package com.everis.pyexamencomentario

import android.app.Application
import com.everis.pyexamencomentario.data.EverisPrefs

class EverisApp : Application() {

    companion object {
        var prefs : EverisPrefs? = null
    }

    override fun onCreate() {
        super.onCreate()

        prefs = EverisPrefs(applicationContext)
    }
}
