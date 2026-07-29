package com.dompetku.app.presentation.lock

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dompetku.app.R
import com.dompetku.app.databinding.ActivitySetupPinBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SetupPinActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetupPinBinding
    private val viewModel: LockViewModel by viewModels()

    private var isConfirmStep = false

    private val dots by lazy {
        listOf(
            binding.dot1, binding.dot2, binding.dot3,
            binding.dot4, binding.dot5, binding.dot6
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupKeypad()
        observeViewModel()
    }

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
                viewModel.onDigitPressedSetup(digit)
            }
        }

        binding.btnBackspace.setOnClickListener {
            viewModel.onBackspacePressed()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                // UI State
                launch {
                    viewModel.uiState.collect { state ->
                        updateDots(state.pinInput.length)

                        if (state.isError) {
                            showError(state.errorMessage)
                        }
                    }
                }

                // Events
                launch {
                    viewModel.event.collect { event ->
                        when (event) {
                            is LockEvent.PinSetupSuccess -> {
                                Snackbar.make(
                                    binding.root,
                                    "PIN berhasil dibuat!",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                setResult(RESULT_OK)
                                finish()
                            }
                            is LockEvent.Error -> {
                                if (event.message == "CONFIRM_STEP") {
                                    // Pindah ke step konfirmasi
                                    isConfirmStep = true
                                    binding.tvSetupTitle.text = getString(R.string.confirm_pin)
                                    binding.tvSetupSubtitle.text = "Masukkan ulang PIN Anda"
                                    updateDots(0)
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun updateDots(filledCount: Int) {
        dots.forEachIndexed { index, dot ->
            dot.setImageResource(
                if (index < filledCount) R.drawable.bg_pin_dot_filled
                else R.drawable.bg_pin_dot_empty
            )
        }
    }

    private fun showError(message: String) {
        binding.tvSetupError.text = message
        binding.tvSetupError.visibility = View.VISIBLE

        // Reset ke step 1 setelah 1.5 detik
        binding.root.postDelayed({
            if (!isFinishing) {
                isConfirmStep = false
                binding.tvSetupTitle.text = getString(R.string.create_pin)
                binding.tvSetupSubtitle.text = "Masukkan 6 digit PIN"
                binding.tvSetupError.visibility = View.INVISIBLE
                viewModel.clearInput()
            }
        }, 1500)
    }
}
