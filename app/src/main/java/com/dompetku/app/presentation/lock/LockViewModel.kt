package com.dompetku.app.presentation.lock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dompetku.app.util.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// ── UI State ──────────────────────────────────
data class LockUiState(
    val pinInput: String = "",
    val maxLength: Int = 6,
    val isBiometricAvailable: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isComplete: Boolean get() = pinInput.length == maxLength
}

// ── Events ────────────────────────────────────
sealed class LockEvent {
    object UnlockSuccess : LockEvent()
    object PinSetupSuccess : LockEvent()
    data class Error(val message: String) : LockEvent()
}

@HiltViewModel
class LockViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LockUiState())
    val uiState: StateFlow<LockUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<LockEvent>()
    val event: SharedFlow<LockEvent> = _event.asSharedFlow()

    // ── Untuk Setup PIN (2 tahap) ──────────────
    private var firstPin: String = ""
    private var isConfirmStep: Boolean = false

    // ── Input PIN Digit ────────────────────────
    fun onDigitPressed(digit: String) {
        val current = _uiState.value.pinInput
        if (current.length >= _uiState.value.maxLength) return

        _uiState.update {
            it.copy(
                pinInput = current + digit,
                isError = false,
                errorMessage = ""
            )
        }

        // Auto-submit saat PIN lengkap
        if (_uiState.value.pinInput.length == _uiState.value.maxLength) {
            viewModelScope.launch {
                kotlinx.coroutines.delay(200) // delay kecil agar dot terlihat
                verifyPin()
            }
        }
    }

    // ── Hapus Digit Terakhir ───────────────────
    fun onBackspacePressed() {
        val current = _uiState.value.pinInput
        if (current.isEmpty()) return
        _uiState.update {
            it.copy(
                pinInput = current.dropLast(1),
                isError = false
            )
        }
    }

    // ── Verifikasi PIN (untuk unlock) ─────────
    private fun verifyPin() {
        viewModelScope.launch {
            val savedPin = preferencesManager.pinCode.first()
            val inputPin = _uiState.value.pinInput

            if (inputPin == savedPin) {
                _event.emit(LockEvent.UnlockSuccess)
            } else {
                _uiState.update {
                    it.copy(
                        pinInput = "",
                        isError = true,
                        errorMessage = "PIN salah, coba lagi"
                    )
                }
                shakeAnimation()
            }
        }
    }

    // ── Setup PIN Baru (2 langkah) ─────────────
    fun onDigitPressedSetup(digit: String) {
        val current = _uiState.value.pinInput
        if (current.length >= _uiState.value.maxLength) return

        _uiState.update {
            it.copy(
                pinInput = current + digit,
                isError = false,
                errorMessage = ""
            )
        }

        if (_uiState.value.pinInput.length == _uiState.value.maxLength) {
            viewModelScope.launch {
                kotlinx.coroutines.delay(200)
                processSetupPin()
            }
        }
    }

    private fun processSetupPin() {
        val inputPin = _uiState.value.pinInput

        if (!isConfirmStep) {
            // Langkah 1: Simpan PIN pertama
            firstPin = inputPin
            isConfirmStep = true
            _uiState.update {
                it.copy(pinInput = "")
            }
            viewModelScope.launch {
                _event.emit(LockEvent.Error("CONFIRM_STEP")) // signal ke UI
            }
        } else {
            // Langkah 2: Konfirmasi PIN
            if (inputPin == firstPin) {
                // PIN cocok → simpan
                viewModelScope.launch {
                    preferencesManager.setPinCode(inputPin)
                    preferencesManager.setPinEnabled(true)
                    _event.emit(LockEvent.PinSetupSuccess)
                }
            } else {
                // PIN tidak cocok → reset
                firstPin = ""
                isConfirmStep = false
                _uiState.update {
                    it.copy(
                        pinInput = "",
                        isError = true,
                        errorMessage = "PIN tidak cocok, mulai ulang"
                    )
                }
            }
        }
    }

    // ── Reset PIN ──────────────────────────────
    fun resetPin() {
        viewModelScope.launch {
            preferencesManager.setPinEnabled(false)
            preferencesManager.setPinCode("")
            preferencesManager.setBiometricEnabled(false)
        }
    }

    // ── Check Biometric ────────────────────────
    fun setBiometricAvailable(available: Boolean) {
        _uiState.update { it.copy(isBiometricAvailable = available) }
    }

    // ── Animasi error (dipanggil dari UI) ──────
    private fun shakeAnimation() {
        // Signal ke UI untuk shake animation
        viewModelScope.launch {
            _event.emit(LockEvent.Error("SHAKE"))
        }
    }

    // ── Clear input ────────────────────────────
    fun clearInput() {
        _uiState.update { it.copy(pinInput = "", isError = false, errorMessage = "") }
    }
}
