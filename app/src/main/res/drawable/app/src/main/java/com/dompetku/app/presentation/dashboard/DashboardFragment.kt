package com.dompetku.app.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dompetku.app.R
import com.dompetku.app.databinding.FragmentDashboardBinding
import com.dompetku.app.presentation.adapter.TransactionAdapter
import com.dompetku.app.util.CurrencyFormatter
import com.dompetku.app.util.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        transactionAdapter = TransactionAdapter(
            onItemClick = { transaction ->
                // Navigate to detail (Phase berikutnya)
            },
            onItemLongClick = { transaction ->
                // Show delete dialog
                true
            }
        )

        binding.rvRecentTransactions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = transactionAdapter
            setHasFixedSize(false)
        }
    }

    private fun setupClickListeners() {
        binding.btnPrevMonth.setOnClickListener {
            viewModel.navigateMonth(-1)
        }

        binding.btnNextMonth.setOnClickListener {
            viewModel.navigateMonth(1)
        }

        binding.tvSeeAll.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboard_to_transactionList
            )
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                // Observe UI State
                launch {
                    viewModel.uiState.collect { state ->
                        updateUI(state)
                    }
                }

                // Observe Current Month
                launch {
                    viewModel.currentCalendar.collect { calendar ->
                        binding.tvMonthYear.text = DateUtils.formatMonthYear(calendar)
                    }
                }
            }
        }
    }

    private fun updateUI(state: DashboardUiState) {
        with(binding) {
            // ── Balance Card ───────────────────
            tvTotalBalance.text = CurrencyFormatter.format(state.totalBalance)
            tvTotalIncome.text = CurrencyFormatter.format(state.totalIncome)
            tvTotalExpense.text = CurrencyFormatter.format(state.totalExpense)

            // ── Recent Transactions ────────────
            if (state.recentTransactions.isEmpty()) {
                rvRecentTransactions.visibility = View.GONE
                layoutEmpty.visibility = View.VISIBLE
            } else {
                rvRecentTransactions.visibility = View.VISIBLE
                layoutEmpty.visibility = View.GONE
                transactionAdapter.submitList(state.recentTransactions)
            }

            // ── Budget Alerts ──────────────────
            if (state.budgetAlerts.isNotEmpty()) {
                layoutBudgetAlerts.visibility = View.VISIBLE
            } else {
                layoutBudgetAlerts.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
