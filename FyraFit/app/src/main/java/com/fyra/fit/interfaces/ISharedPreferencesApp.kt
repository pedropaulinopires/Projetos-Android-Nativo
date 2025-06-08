package com.fyra.fit.interfaces

import com.fyra.fit.utils.enums.KeysSharedPreferences

interface ISharedPreferencesApp {

    fun saveBoolValue(key: KeysSharedPreferences, value: Boolean): Boolean

    fun getBoolValue(key: KeysSharedPreferences): Boolean

    fun saveStringValue(key: KeysSharedPreferences, value: String): Boolean

    fun getStringValue(key: KeysSharedPreferences): String
}