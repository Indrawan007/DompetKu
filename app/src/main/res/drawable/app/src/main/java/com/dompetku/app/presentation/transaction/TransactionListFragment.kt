package com.dompetku.app.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dompetku.app.databinding.FragmentTransactionListBinding
import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.presentation.adapter.TransactionAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionListFragment : Fragment() {

    private var _binding: FragmentTransactionListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransactionViewModel by activityViewModels()
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        transactionAdapter = TransactionAdapter(
            onItemClick = { transaction ->
                // TODO: Navigate to detail
            },
            onItemLongClick = { transaction ->
                showDeleteDialog(transaction)
                true
            }
        )

        binding.rvTransactions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = transactionAdapter
        }
    }

    private fun setupSearch() {
        binding.etSearch.doAfterTextChanged { text ->
            viewModel.setSearchQuery(text.toString())
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.transactions.collect { transactions ->
                        transactionAdapter.submitList(transactions)

                        if (transactions.isEmpty()) {
                            binding.rvTransactions.visibility = View.GONE
                            binding.layoutEmpty.visibility = View.VISIBLE
                        } else {
                            binding.rvTransactions.visibility = View.VISIBLE
                            binding.layoutEmpty.visibility = View.GONE
                        }
                    }
                }

                launch {
                    viewModel.event.collect { event ->
                        when (event) {
                            is TransactionEvent.Success -> {
                                Snackbar.make(
                                    binding.root,
                                    event.message,
                                    Snackbar.LENGTH_SHORT
                                ).show()
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

    private fun showDeleteDialog(transaction: Transaction) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Transaksi")
            .setMessage("Yakin ingin menghapus transaksi ini?")
            .setPositiveButton("Hapus") { _, _ ->
                viewModel.deleteTransaction(transaction)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
