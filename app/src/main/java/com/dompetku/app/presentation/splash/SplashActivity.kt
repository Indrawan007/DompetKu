package com.dompetku.app.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dompetku.app.presentation.lock.LockActivity
import com.dompetku.app.presentation.main.MainActivity
import com.dompetku.app.util.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            // Tampilkan splash sebentar
            delay(1000)

            // Cek apakah PIN aktif
            val isPinEnabled = preferencesManager.isPinEnabled.first()

            val destination = if (isPinEnabled) {
                LockActivity::class.java
            } else {
                MainActivity::class.java
            }

            startActivity(Intent(this@SplashActivity, destination))
            finish()
        }
    }
}
