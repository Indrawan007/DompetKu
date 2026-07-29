package com.dompetku.app.presentation.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dompetku.app.R
import com.dompetku.app.databinding.FragmentSettingsBinding
import com.dompetku.app.presentation.lock.SetupPinActivity
import com.dompetku.app.receiver.ReminderWorker
import com.dompetku.app.util.NotificationHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModels()

    private val pinSetupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode != Activity.RESULT_OK) {
            binding.switchPin.isChecked = false
        }
    }

    private val restoreLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { viewModel.restore(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NotificationHelper.createChannels(requireContext())
        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.switchPin.setOnCheckedChangeListener { _, isChecked ->
            viewModel.togglePin(isChecked)
        }
        binding.switchBiometric.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleBiometric(isChecked)
        }
        binding.layoutTheme.setOnClickListener { showThemeDialog() }
        binding.switchReminder.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleReminder(isChecked)
            if (isChecked) showTimePicker()
            else {
                ReminderWorker.cancel(requireContext())
                binding.tvReminderTime.text = "Nonaktif"
            }
        }
        binding.layoutReminder.setOnClickListener {
            if (binding.switchReminder.isChecked) showTimePicker()
        }
        binding.layoutBackup.setOnClickListener { viewModel.backup() }
        binding.layoutRestore.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Restore Data")
                .setMessage("Data saat ini akan diganti. Lanjutkan?")
                .setPositiveButton("Lanjutkan") { _, _ ->
                    restoreLauncher.launch("application/octet-stream")
                }
                .setNegativeButton("Batal", null)
                .show()
        }
        binding.layoutReset.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.dialog_reset_title))
                .setMessage(getString(R.string.dialog_reset_message))
                .setPositiveButton("Hapus Semua") { _, _ -> viewModel.resetAllData() }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    private fun showThemeDialog() {
        val themes = arrayOf("Ikuti Sistem", "Terang", "Gelap")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Pilih Tema")
            .setSingleChoiceItems(themes, viewModel.themeMode.value) { dialog, which ->
                viewModel.setTheme(which)
                AppCompatDelegate.setDefaultNightMode(
                    when (which) {
                        1 -> AppCompatDelegate.MODE_NIGHT_NO
                        2 -> AppCompatDelegate.MODE_NIGHT_YES
                        else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                )
                dialog.dismiss()
            }
            .show()
    }

    private fun showTimePicker() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(viewModel.reminderHour.value)
            .setMinute(viewModel.reminderMinute.value)
            .setTitleText("Waktu Pengingat")
            .build()
        picker.addOnPositiveButtonClickListener {
            val h = picker.hour
            val m = picker.minute
            viewModel.setReminderTime(h, m)
            ReminderWorker.schedule(requireContext(), h, m)
            binding.tvReminderTime.text = String.format("%02d:%02d", h, m)
        }
        picker.show(parentFragmentManager, "TIME_PICKER")
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.isPinEnabled.collect { enabled ->
                        binding.switchPin.isChecked = enabled
                        binding.layoutBiometric.visibility =
                            if (enabled) View.VISIBLE else View.GONE
                    }
                }
                launch {
                    viewModel.isBiometricEnabled.collect { enabled ->
                        binding.switchBiometric.isChecked = enabled
                    }
                }
                launch {
                    viewModel.themeMode.collect { mode ->
                        val names = arrayOf(
                            getString(R.string.settings_theme_system),
                            getString(R.string.settings_theme_light),
                            getString(R.string.settings_theme_dark)
                        )
                        binding.tvThemeValue.text = names.getOrElse(mode) { names[0] }
                    }
                }
                launch {
                    viewModel.isReminderEnabled.collect { enabled ->
                        binding.switchReminder.isChecked = enabled
                        binding.tvReminderTime.text = if (enabled) {
                            String.format(
                                "%02d:%02d",
                                viewModel.reminderHour.value,
                                viewModel.reminderMinute.value
                            )
                        } else "Nonaktif"
                    }
                }
                launch {
                    viewModel.event.collect { event ->
                        when (event) {
                            is SettingsEvent.Success ->
                                Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG).show()
                            is SettingsEvent.Error ->
                                Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                            is SettingsEvent.PinSetupRequired ->
                                pinSetupLauncher.launch(
                                    Intent(requireContext(), SetupPinActivity::class.java)
                                )
                            is SettingsEvent.RestartRequired ->
                                Snackbar.make(binding.root, "Aplikasi perlu dimulai ulang", Snackbar.LENGTH_LONG)
                                    .setAction("Restart") { requireActivity().finishAffinity() }
                                    .show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
