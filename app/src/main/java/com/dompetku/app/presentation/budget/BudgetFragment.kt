package com.dompetku.app.presentation.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dompetku.app.databinding.FragmentBudgetBinding
import com.dompetku.app.domain.model.Budget
import com.dompetku.app.presentation.adapter.BudgetAdapter
import com.dompetku.app.util.CurrencyFormatter
import com.dompetku.app.util.DateUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class BudgetFragment : Fragment() {

    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BudgetViewModel by viewModels<BudgetViewModel>()
    private var budgetAdapter: BudgetAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        budgetAdapter = BudgetAdapter(
            onItemClick = { budget: Budget ->
                SetBudgetBottomSheet.newInstance(budget).show(
                    childFragmentManager, SetBudgetBottomSheet.TAG
                )
            },
            onItemLongClick = { budget: Budget ->
                showDeleteDialog(budget)
                true
            }
        )
        binding.rvBudgets.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = budgetAdapter
        }
    }

    private fun setupClickListeners() {
        binding.btnPrevMonth.setOnClickListener { viewModel.navigateMonth(-1) }
        binding.btnNextMonth.setOnClickListener { viewModel.navigateMonth(1) }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.currentCalendar.collect { cal: Calendar ->
                        binding.tvMonthYear.text = DateUtils.formatMonthYear(cal)
                    }
                }

                launch {
                    viewModel.budgets.collect { budgets: List<Budget> ->
                        budgetAdapter?.submitList(budgets)
                        if (budgets.isEmpty()) {
                            binding.rvBudgets.visibility = View.GONE
                            binding.layoutEmpty.visibility = View.VISIBLE
                        } else {
                            binding.rvBudgets.visibility = View.VISIBLE
                            binding.layoutEmpty.visibility = View.GONE
                        }
                        binding.tvTotalBudget.text = CurrencyFormatter.format(
                            budgets.sumOf { it.amountLimit }
                        )
                        binding.tvTotalSpent.text = CurrencyFormatter.format(
                            budgets.sumOf { it.spentAmount }
                        )
                        binding.tvTotalRemaining.text = CurrencyFormatter.format(
                            budgets.sumOf { maxOf(it.remainingAmount, 0.0) }
                        )
                    }
                }

                launch {
                    viewModel.event.collect { event: BudgetEvent ->
                        val msg = when (event) {
                            is BudgetEvent.Success -> event.message
                            is BudgetEvent.Error -> event.message
                        }
                        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showDeleteDialog(budget: Budget) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Anggaran")
            .setMessage("Hapus anggaran ${budget.categoryName}?")
            .setPositiveButton("Hapus") { _, _ -> viewModel.deleteBudget(budget) }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        budgetAdapter = null
    }
}
