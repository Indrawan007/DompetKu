package com.dompetku.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.dompetku.app.util.NotificationHelper
import com.dompetku.app.util.PreferencesManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class DompetKuApp : Application() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createChannels(this)
        applyTheme()
    }

    private fun applyTheme() {
        appScope.launch {
            val mode = preferencesManager.themeMode.first()
            val nightMode = when (mode) {
                1 -> AppCompatDelegate.MODE_NIGHT_NO
                2 -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
            AppCompatDelegate.setDefaultNightMode(nightMode)
        }
    }
}
