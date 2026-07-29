package com.dompetku.app.presentation.lock

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dompetku.app.R
import com.dompetku.app.databinding.ActivityLockBinding
import com.dompetku.app.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLockBinding
    private val viewModel: LockViewModel by viewModels()

    private val dots by lazy {
        listOf(
            binding.dot1, binding.dot2, binding.dot3,
            binding.dot4, binding.dot5, binding.dot6
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupKeypad()
        setupBiometric()
        observeViewModel()
    }

    // ── Setup Keypad ──────────────────────────
    private fun setupKeypad() {
        val buttons = mapOf(
            binding.btn0 to "0", binding.btn1 to "1",
            binding.btn2 to "2", binding.btn3 to "3",
            binding.btn4 to "4", binding.btn5 to "5",
            binding.btn6 to "6", binding.btn7 to "7",
            binding.btn8 to "8", binding.btn9 to "9"
        )

        buttons.forEach { (button, digit) ->
            button.setOnClickListener {
                viewModel.onDigitPressed(digit)
            }
        }

        binding.btnBackspace.setOnClickListener {
            viewModel.onBackspacePressed()
        }

        binding.btnBiometric.setOnClickListener {
            showBiometricPrompt()
        }
    }

    // ── Setup Biometric ───────────────────────
    private fun setupBiometric() {
        val biometricManager = BiometricManager.from(this)
        val canAuthenticate = biometricManager.canAuthenticate(BIOMETRIC_STRONG)

        if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            binding.btnBiometric.visibility = View.VISIBLE
            viewModel.setBiometricAvailable(true)
            // Auto-trigger biometric saat buka
            showBiometricPrompt()
        } else {
            binding.btnBiometric.visibility = View.INVISIBLE
        }
    }

    // ── Biometric Prompt ──────────────────────
    private fun showBiometricPrompt() {
        val executor = ContextCompat.getMainExecutor(this)

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
                super.onAuthenticationSucceeded(result)
                navigateToMain()
            }

            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                super.onAuthenticationError(errorCode, errString)
                // User cancel → biarkan masukkan PIN
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                showError("Autentikasi gagal")
            }
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_title))
            .setSubtitle(getString(R.string.biometric_subtitle))
            .setNegativeButtonText("Gunakan PIN")
            .build()

        BiometricPrompt(this, executor, callback).authenticate(promptInfo)
    }

    // ── Observe ViewModel ─────────────────────
    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                // Observe UI State
                launch {
                    viewModel.uiState.collect { state ->
                        updateDots(state.pinInput.length)

                        if (state.isError) {
                            showError(state.errorMessage)
                        } else {
                            binding.tvPinSubtitle.visibility = View.INVISIBLE
                        }
                    }
                }

                // Observe Events
                launch {
                    viewModel.event.collect { event ->
                        when (event) {
                            is LockEvent.UnlockSuccess -> navigateToMain()
                            is LockEvent.Error -> {
                                if (event.message == "SHAKE") {
                                    shakeDotsAnimation()
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    // ── Update PIN Dots ───────────────────────
    private fun updateDots(filledCount: Int) {
        dots.forEachIndexed { index, dot ->
            dot.setImageResource(
                if (index < filledCount) R.drawable.bg_pin_dot_filled
                else R.drawable.bg_pin_dot_empty
            )
        }
    }

    // ── Show Error ────────────────────────────
    private fun showError(message: String) {
        binding.tvPinSubtitle.text = message
        binding.tvPinSubtitle.setTextColor(
            ContextCompat.getColor(this, R.color.expense_red)
        )
        binding.tvPinSubtitle.visibility = View.VISIBLE

        // Ubah dots jadi merah
        dots.forEach { dot ->
            dot.setImageResource(R.drawable.bg_pin_dot_error)
        }

        // Reset dots setelah 1 detik
        binding.layoutPinDots.postDelayed({
            updateDots(0)
            binding.tvPinSubtitle.visibility = View.INVISIBLE
        }, 1000)
    }

    // ── Shake Animation ───────────────────────
    private fun shakeDotsAnimation() {
        val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
        binding.layoutPinDots.startAnimation(shake)
    }

    // ── Navigate to Main ──────────────────────
    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    // Tidak bisa back dari lock screen
    override fun onBackPressed() {
        // Do nothing - prevent back
    }
}
