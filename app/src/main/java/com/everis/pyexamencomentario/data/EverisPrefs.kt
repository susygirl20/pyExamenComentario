package com.everis.pyexamencomentario.data

import android.content.Context
import android.content.SharedPreferences

class EverisPrefs (context: Context) {

    val PREF_FILENAME ="com.everis.pyexamencomentario"

    val prefs : SharedPreferences = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE )

    //Boolean

    fun putBoolean(key:String, value:Boolean){
        prefs.edit().putBoolean(key, value)
    }

    fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    //String

    fun putString(key:String, value:String){
        prefs.edit().putString(key, value)
    }

    fun getString(key: String): String {
        return prefs.getString(key, null).toString()
    }
}