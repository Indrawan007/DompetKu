package com.dompetku.app.presentation.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dompetku.app.databinding.FragmentSetBudgetBinding
import com.dompetku.app.domain.model.Budget
import com.dompetku.app.domain.model.Category
import com.dompetku.app.util.CurrencyFormatter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SetBudgetBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentSetBudgetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BudgetViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    private var selectedCategory: Category? = null
    private var categoryList: List<Category> = emptyList()
    private var editBudget: Budget? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupSaveButton()

        // Jika edit, pre-fill data
        editBudget?.let { budget ->
            binding.etAmount.setText(
                CurrencyFormatter.formatWithoutSymbol(budget.amountLimit)
            )
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.expenseCategories.collect { categories ->
                        categoryList = categories
                        val names = categories.map { it.name }
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            names
                        )
                        binding.dropdownCategory.setAdapter(adapter)
                        binding.dropdownCategory.setOnItemClickListener { _, _, position, _ ->
                            selectedCategory = categoryList[position]
                        }

                        // Pre-select jika edit
                        editBudget?.let { budget ->
                            val idx = categories.indexOfFirst { it.id == budget.categoryId }
                            if (idx >= 0) {
                                selectedCategory = categories[idx]
                                binding.dropdownCategory.setText(categories[idx].name, false)
                            }
                        }
                    }
                }

                launch {
                    viewModel.event.collect { event ->
                        when (event) {
                            is BudgetEvent.Success -> {
                                Snackbar.make(
                                    requireActivity().window.decorView,
                                    event.message,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                dismiss()
                            }
                            is BudgetEvent.Error -> {
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

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val amountText = binding.etAmount.text.toString()

            if (selectedCategory == null) {
                Snackbar.make(binding.root, "Pilih kategori", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (amountText.isBlank()) {
                binding.tilAmount.error = "Masukkan jumlah anggaran"
                return@setOnClickListener
            }

            val amount = CurrencyFormatter.parse(amountText)
            if (amount <= 0) {
                binding.tilAmount.error = "Jumlah harus lebih dari 0"
                return@setOnClickListener
            }

            binding.tilAmount.error = null
            viewModel.setBudget(selectedCategory!!, amount)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "SetBudgetBottomSheet"

        fun newInstance(budget: Budget? = null): SetBudgetBottomSheet {
            return SetBudgetBottomSheet().apply {
                editBudget = budget
            }
        }
    }
}
