package com.dompetku.app.presentation.settings

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dompetku.app.util.BackupManager
import com.dompetku.app.util.PreferencesManager
import com.dompetku.app.util.ResetManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SettingsEvent {
    data class Success(val message: String) : SettingsEvent()
    data class Error(val message: String) : SettingsEvent()
    object PinSetupRequired : SettingsEvent()
    object RestartRequired : SettingsEvent()
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val backupManager: BackupManager,
    private val resetManager: ResetManager
) : ViewModel() {

    private val _event = MutableSharedFlow<SettingsEvent>()
    val event: SharedFlow<SettingsEvent> = _event.asSharedFlow()

    val isPinEnabled: StateFlow<Boolean> = preferencesManager.isPinEnabled
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val isBiometricEnabled: StateFlow<Boolean> = preferencesManager.isBiometricEnabled
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val themeMode: StateFlow<Int> = preferencesManager.themeMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val isReminderEnabled: StateFlow<Boolean> = preferencesManager.isReminderEnabled
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val reminderHour: StateFlow<Int> = preferencesManager.reminderHour
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 20)

    val reminderMinute: StateFlow<Int> = preferencesManager.reminderMinute
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun togglePin(enabled: Boolean) {
        if (enabled) {
            viewModelScope.launch { _event.emit(SettingsEvent.PinSetupRequired) }
        } else {
            viewModelScope.launch {
                preferencesManager.setPinEnabled(false)
                preferencesManager.setPinCode("")
                preferencesManager.setBiometricEnabled(false)
            }
        }
    }

    fun toggleBiometric(enabled: Boolean) {
        viewModelScope.launch { preferencesManager.setBiometricEnabled(enabled) }
    }

    fun setTheme(mode: Int) {
        viewModelScope.launch { preferencesManager.setThemeMode(mode) }
    }

    fun toggleReminder(enabled: Boolean) {
        viewModelScope.launch { preferencesManager.setReminderEnabled(enabled) }
    }

    fun setReminderTime(hour: Int, minute: Int) {
        viewModelScope.launch { preferencesManager.setReminderTime(hour, minute) }
    }

    fun backup() {
        viewModelScope.launch {
            backupManager.exportBackup().fold(
                onSuccess = { path -> _event.emit(SettingsEvent.Success("Backup berhasil:\n$path")) },
                onFailure = { err -> _event.emit(SettingsEvent.Error(err.message ?: "Backup gagal")) }
            )
        }
    }

    fun restore(uri: Uri) {
        viewModelScope.launch {
            backupManager.importBackup(uri).fold(
                onSuccess = { _event.emit(SettingsEvent.RestartRequired) },
                onFailure = { err -> _event.emit(SettingsEvent.Error(err.message ?: "Restore gagal")) }
            )
        }
    }

    fun resetAllData() {
        viewModelScope.launch {
            resetManager.resetAll().fold(
                onSuccess = { _event.emit(SettingsEvent.RestartRequired) },
                onFailure = { err -> _event.emit(SettingsEvent.Error(err.message ?: "Reset gagal")) }
            )
        }
    }
}
