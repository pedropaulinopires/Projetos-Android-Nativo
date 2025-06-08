package com.fyra.fit.repository

import android.content.SharedPreferences
import com.fyra.fit.interfaces.ISharedPreferencesApp
import com.fyra.fit.utils.enums.KeysSharedPreferences


class SharedPreferencesApp(private val sharedPreferences: SharedPreferences) :
    ISharedPreferencesApp {

    override fun saveBoolValue(key: KeysSharedPreferences, value: Boolean): Boolean =
        sharedPreferences.edit().putBoolean(key.toString(), value).commit()

    override fun getBoolValue(key: KeysSharedPreferences): Boolean =
        sharedPreferences.getBoolean(key.toString(), false)

    override fun saveStringValue(
        key: KeysSharedPreferences, value: String
    ): Boolean = sharedPreferences.edit().putString(key.toString(), value).commit()

    override fun getStringValue(key: KeysSharedPreferences): String =
        sharedPreferences.getString(key.toString(), "").toString()


}