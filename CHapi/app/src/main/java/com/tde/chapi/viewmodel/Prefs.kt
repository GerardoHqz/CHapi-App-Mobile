package com.tde.chapi.viewmodel

import android.content.Context

class Prefs(val context: Context) {
    val SHARED_NAME = "mydtb"
    val SHARED_TOKEN = "token"
    val SHARED_USERNAME = "username"
    val SHARED_VIP = "vip"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveName(name: String) {
        storage.edit().putString(SHARED_USERNAME, name).apply()
    }

    fun saveToken(token: String) {
        storage.edit().putString(SHARED_TOKEN, token).apply()
    }

    fun saveVIP(vip: Boolean) {
        storage.edit().putBoolean(SHARED_VIP, vip).apply()
    }

    fun getName(): String {
        return storage.getString(SHARED_USERNAME, "")!!
    }

    fun getToken(): String {
        return storage.getString(SHARED_TOKEN, "")!!
    }

    fun getVIP(): Boolean {
        return storage.getBoolean(SHARED_VIP, false)!!
    }

    fun wipe() {
        storage.edit().clear().apply()
    }
}