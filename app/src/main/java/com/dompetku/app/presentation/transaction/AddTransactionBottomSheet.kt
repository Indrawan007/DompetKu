package com.dompetku.app.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dompetku.app.R
import com.dompetku.app.databinding.FragmentAddTransactionBinding
import com.dompetku.app.domain.model.Account
import com.dompetku.app.domain.model.Category
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.util.CurrencyFormatter
import com.dompetku.app.util.DateUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Date

@AndroidEntryPoint
class AddTransactionBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransactionViewModel by activityViewModels<TransactionViewModel>()

    private var selectedType: TransactionType = TransactionType.EXPENSE
    private var selectedCategory: Category? = null
    private var selectedAccount: Account? = null
    private var selectedDate: Date = Date()
    private var categoryList: List<Category> = emptyList()
    private var accountList: List<Account> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTypeToggle()
        setupDatePicker()
        setupSaveButton()
        observeViewModel()
    }

    private fun setupTypeToggle() {
        binding.toggleType.check(R.id.btn_expense)
        binding.toggleType.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                selectedType = when (checkedId) {
                    R.id.btn_income -> TransactionType.INCOME
                    else -> TransactionType.EXPENSE
                }
                selectedCategory = null
                binding.dropdownCategory.setText("")
                updateCategoryDropdown()
            }
        }
    }

    private fun setupDatePicker() {
        binding.etDate.setText(DateUtils.formatDateFull(selectedDate))
        val listener = View.OnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih Tanggal")
                .setSelection(selectedDate.time)
                .build()
            picker.addOnPositiveButtonClickListener { millis: Long ->
                selectedDate = Date(millis)
                binding.etDate.setText(DateUtils.formatDateFull(selectedDate))
            }
            picker.show(parentFragmentManager, "DATE_PICKER")
        }
        binding.etDate.setOnClickListener(listener)
        binding.tilDate.setEndIconOnClickListener(listener)
    }

    private fun updateCategoryDropdown() {
        val filtered: List<Category> = if (selectedType == TransactionType.EXPENSE)
            viewModel.expenseCategories.value
        else
            viewModel.incomeCategories.value

        categoryList = filtered
        val names: List<String> = filtered.map { cat: Category -> cat.name }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            names
        )
        binding.dropdownCategory.setAdapter(adapter)
        binding.dropdownCategory.setOnItemClickListener { _, _, pos, _ ->
            selectedCategory = categoryList[pos]
        }
    }

    private fun updateAccountDropdown() {
        val names: List<String> = accountList.map { acc: Account -> acc.name }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            names
        )
        binding.dropdownAccount.setAdapter(adapter)
        binding.dropdownAccount.setOnItemClickListener { _, _, pos, _ ->
            selectedAccount = accountList[pos]
        }
        if (accountList.isNotEmpty()) {
            selectedAccount = accountList[0]
            binding.dropdownAccount.setText(accountList[0].name, false)
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val amountText: String = binding.etAmount.text.toString()
            if (amountText.isBlank()) {
                binding.tilAmount.error = getString(R.string.error_amount_invalid)
                return@setOnClickListener
            }
            val amount: Double = CurrencyFormatter.parse(amountText)
            if (amount <= 0) {
                binding.tilAmount.error = getString(R.string.error_amount_zero)
                return@setOnClickListener
            }
            val account: Account = selectedAccount ?: run {
                Snackbar.make(
                    binding.root,
                    getString(R.string.error_select_account),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            binding.tilAmount.error = null
            viewModel.addTransaction(
                amount = amount,
                type = selectedType,
                category = selectedCategory,
                account = account,
                note = binding.etNote.text.toString().trim(),
                date = selectedDate
            )
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.expenseCategories.collect { categories: List<Category> ->
                        if (selectedType == TransactionType.EXPENSE) {
                            categoryList = categories
                            updateCategoryDropdown()
                        }
                    }
                }

                launch {
                    viewModel.incomeCategories.collect { categories: List<Category> ->
                        if (selectedType == TransactionType.INCOME) {
                            categoryList = categories
                            updateCategoryDropdown()
                        }
                    }
                }

                launch {
                    viewModel.accounts.collect { accounts: List<Account> ->
                        accountList = accounts
                        updateAccountDropdown()
                    }
                }

                launch {
                    viewModel.event.collect { event: TransactionEvent ->
                        when (event) {
                            is TransactionEvent.Success -> {
                                Snackbar.make(
                                    requireActivity().window.decorView,
                                    event.message,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                dismiss()
                            }
                            is TransactionEvent.Error -> {
                                Snackbar.make(
                                    binding.root,
                                    event.message,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
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

    companion object {
        const val TAG = "AddTransactionBottomSheet"
    }
}
