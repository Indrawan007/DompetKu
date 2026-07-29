package com.dompetku.app.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension untuk membuat DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "dompetku_preferences"
)

class PreferencesManager(private val context: Context) {

    companion object {
        // Security
        val KEY_PIN_ENABLED = booleanPreferencesKey("pin_enabled")
        val KEY_PIN_CODE = stringPreferencesKey("pin_code")
        val KEY_BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")

        // Theme
        val KEY_THEME_MODE = intPreferencesKey("theme_mode")
        // 0 = System, 1 = Light, 2 = Dark

        // Reminder
        val KEY_REMINDER_ENABLED = booleanPreferencesKey("reminder_enabled")
        val KEY_REMINDER_HOUR = intPreferencesKey("reminder_hour")
        val KEY_REMINDER_MINUTE = intPreferencesKey("reminder_minute")

        // First Launch
        val KEY_FIRST_LAUNCH = booleanPreferencesKey("first_launch")

        // Currency
        val KEY_CURRENCY = stringPreferencesKey("currency")
    }

    // ══════════════════════════════════════
    // PIN & Security
    // ══════════════════════════════════════
    val isPinEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_PIN_ENABLED] ?: false
    }

    val pinCode: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[KEY_PIN_CODE] ?: ""
    }

    val isBiometricEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_BIOMETRIC_ENABLED] ?: false
    }

    suspend fun setPinEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_PIN_ENABLED] = enabled
        }
    }

    suspend fun setPinCode(pin: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_PIN_CODE] = pin
        }
    }

    suspend fun setBiometricEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_BIOMETRIC_ENABLED] = enabled
        }
    }

    // ══════════════════════════════════════
    // Theme
    // ══════════════════════════════════════
    val themeMode: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[KEY_THEME_MODE] ?: 0 // default: follow system
    }

    suspend fun setThemeMode(mode: Int) {
        context.dataStore.edit { prefs ->
            prefs[KEY_THEME_MODE] = mode
        }
    }

    // ══════════════════════════════════════
    // Reminder
    // ══════════════════════════════════════
    val isReminderEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_REMINDER_ENABLED] ?: false
    }

    val reminderHour: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[KEY_REMINDER_HOUR] ?: 20 // default: jam 8 malam
    }

    val reminderMinute: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[KEY_REMINDER_MINUTE] ?: 0
    }

    suspend fun setReminderEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_REMINDER_ENABLED] = enabled
        }
    }

    suspend fun setReminderTime(hour: Int, minute: Int) {
        context.dataStore.edit { prefs ->
            prefs[KEY_REMINDER_HOUR] = hour
            prefs[KEY_REMINDER_MINUTE] = minute
        }
    }

    // ══════════════════════════════════════
    // First Launch
    // ══════════════════════════════════════
    val isFirstLaunch: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_FIRST_LAUNCH] ?: true
    }

    suspend fun setFirstLaunchDone() {
        context.dataStore.edit { prefs ->
            prefs[KEY_FIRST_LAUNCH] = false
        }
    }

    // ══════════════════════════════════════
    // Clear All
    // ══════════════════════════════════════
    suspend fun clearAll() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}