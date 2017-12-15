package com.tutorial.learnlinuxpro.presentation.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by kodeartisan on 23/10/17.
 */
object PreferencesUtil {

   // private lateinit var sharedPreferences: SharedPreferences
    val RATE_IT: String = "RATE_IT"
    private val SETTING: String = "SETTING"

    private val sharedPreferences: SharedPreferences = Util.getContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE)


    init {

    }



    fun putInt(key: String, value: Int): Boolean {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putInt(key, value)

        return editor.commit()
    }

    fun putBoolean(key: String, value: Boolean): Boolean {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putBoolean(key, value)

        return editor.commit()
    }

    fun putString(key: String, value: String): Boolean {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(key, value)

        return editor.commit()
    }

    fun getInt(key: String, defaultValue: Int): Int = sharedPreferences.getInt(key, defaultValue)

    fun getString(key: String, defaultValue: String): String  = sharedPreferences.getString(key, defaultValue)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean = sharedPreferences.getBoolean(key, defaultValue)


}