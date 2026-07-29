package com.dompetku.app.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dompetku.app.R
import com.dompetku.app.databinding.ItemTransactionBinding
import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.util.CurrencyFormatter
import com.dompetku.app.util.DateUtils
import com.dompetku.app.util.IconMapper

class TransactionAdapter(
    private val onItemClick: (Transaction) -> Unit,
    private val onItemLongClick: (Transaction) -> Boolean = { false }
) : ListAdapter<Transaction, TransactionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemTransactionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            with(binding) {
                // Icon
                val iconRes: Int = IconMapper.getCategoryIcon(transaction.categoryIcon)
                ivCategoryIcon.setImageResource(iconRes)
                try {
                    val color: Int = Color.parseColor(transaction.categoryColor ?: "#607D8B")
                    ivCategoryIcon.backgroundTintList = ColorStateList.valueOf(color)
                } catch (e: Exception) {
                    ivCategoryIcon.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(root.context, R.color.category_other)
                    )
                }

                // Name
                tvCategoryName.text = transaction.categoryName
                    ?: root.context.getString(R.string.cat_other)

                // Note
                tvNote.text = if (transaction.note.isBlank()) "-" else transaction.note

                // Date
                tvDate.text = DateUtils.formatRelative(transaction.date)

                // Account
                tvAccountName.text = transaction.accountName ?: ""

                // Amount
                when (transaction.type) {
                    TransactionType.INCOME -> {
                        tvAmount.text = "+${CurrencyFormatter.format(transaction.amount)}"
                        tvAmount.setTextColor(
                            ContextCompat.getColor(root.context, R.color.income_green)
                        )
                    }
                    TransactionType.EXPENSE -> {
                        tvAmount.text = "-${CurrencyFormatter.format(transaction.amount)}"
                        tvAmount.setTextColor(
                            ContextCompat.getColor(root.context, R.color.expense_red)
                        )
                    }
                }

                root.setOnClickListener { onItemClick(transaction) }
                root.setOnLongClickListener { onItemLongClick(transaction) }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(
            oldItem: Transaction, newItem: Transaction
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Transaction, newItem: Transaction
        ): Boolean = oldItem == newItem
    }
}
