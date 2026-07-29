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
import com.dompetku.app.databinding.ItemBudgetBinding
import com.dompetku.app.domain.model.Budget
import com.dompetku.app.util.CurrencyFormatter
import com.dompetku.app.util.IconMapper

class BudgetAdapter(
    private val onItemClick: (Budget) -> Unit = {},
    private val onItemLongClick: (Budget) -> Boolean = { false }
) : ListAdapter<Budget, BudgetAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBudgetBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBudgetBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(budget: Budget) {
            with(binding) {
                // ── Category Icon ──────────────
                val iconRes = IconMapper.getCategoryIcon(budget.categoryIcon)
                ivCategoryIcon.setImageResource(iconRes)

                try {
                    val color = Color.parseColor(budget.categoryColor)
                    ivCategoryIcon.backgroundTintList = ColorStateList.valueOf(color)
                } catch (e: Exception) {
                    ivCategoryIcon.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(root.context, R.color.primary)
                    )
                }

                // ── Category Name ──────────────
                tvCategoryName.text = budget.categoryName

                // ── Status Badge ───────────────
                val percentage = budget.percentage.toInt()
                tvStatus.text = "$percentage%"

                val badgeColor = when {
                    budget.isOverBudget -> R.color.expense_red
                    budget.isNearLimit  -> R.color.budget_warning
                    else                -> R.color.income_green
                }
                tvStatus.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(root.context, badgeColor)
                )

                // ── Progress Bar ───────────────
                val progress = minOf(percentage, 100)
                progressBudget.progress = progress

                val progressColor = when {
                    budget.isOverBudget -> R.color.expense_red
                    budget.isNearLimit  -> R.color.budget_warning
                    else                -> R.color.income_green
                }
                progressBudget.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(root.context, progressColor)
                )

                // ── Amounts ────────────────────
                tvSpent.text = CurrencyFormatter.format(budget.spentAmount)
                tvLimit.text = CurrencyFormatter.format(budget.amountLimit)

                val remaining = budget.remainingAmount
                tvRemaining.text = CurrencyFormatter.format(
                    if (remaining < 0) 0.0 else remaining
                )
                tvRemaining.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        if (budget.isOverBudget) R.color.expense_red
                        else R.color.income_green
                    )
                )

                // ── Click ──────────────────────
                root.setOnClickListener { onItemClick(budget) }
                root.setOnLongClickListener { onItemLongClick(budget) }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Budget>() {
        override fun areItemsTheSame(oldItem: Budget, newItem: Budget) =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Budget, newItem: Budget) =
            oldItem == newItem
    }
}
