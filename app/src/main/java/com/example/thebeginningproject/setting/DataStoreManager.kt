package com.example.thebeginningproject.setting

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(private val context: Context) {

    suspend fun saveSettings(settingData: SettingData) {
        context.dataStore.edit { pref ->
            pref[intPreferencesKey("counter")] = settingData.counter
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingData(
            pref[intPreferencesKey("counter")] ?: 0,
        )
    }
}

data class SettingData(
    val counter: Int
)