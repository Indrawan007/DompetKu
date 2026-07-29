package com.dompetku.app.presentation.report

import android.graphics.Color
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
import com.dompetku.app.databinding.FragmentReportBinding
import com.dompetku.app.presentation.adapter.CategorySummaryAdapter
import com.dompetku.app.util.CurrencyFormatter
import com.dompetku.app.util.DateUtils
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReportViewModel by viewModels<ReportViewModel>()
    private var categorySummaryAdapter: CategorySummaryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupCharts()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        categorySummaryAdapter = CategorySummaryAdapter()
        binding.rvCategorySummary.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categorySummaryAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun setupCharts() {
        binding.pieChart.apply {
            description.isEnabled = false
            isRotationEnabled = true
            setUsePercentValues(true)
            setDrawEntryLabels(false)
            legend.apply {
                isEnabled = true
                orientation = Legend.LegendOrientation.VERTICAL
                horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                verticalAlignment = Legend.LegendVerticalAlignment.CENTER
                setDrawInside(false)
            }
            setNoDataText("Belum ada data")
        }
        binding.barChart.apply {
            description.isEnabled = false
            setDrawGridBackground(false)
            isDoubleTapToZoomEnabled = false
            setPinchZoom(false)
            setScaleEnabled(false)
            axisLeft.axisMinimum = 0f
            axisRight.isEnabled = false
            setNoDataText("Belum ada data")
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
                        binding.tvPeriod.text = DateUtils.formatMonthYear(cal)
                    }
                }

                launch {
                    viewModel.uiState.collect { state: ReportUiState ->
                        if (!state.isLoading) updateUI(state)
                    }
                }
            }
        }
    }

    private fun updateUI(state: ReportUiState) {
        binding.tvTotalIncome.text = CurrencyFormatter.format(state.totalIncome)
        binding.tvTotalExpense.text = CurrencyFormatter.format(state.totalExpense)
        binding.tvNetBalance.text = CurrencyFormatter.format(state.netBalance)

        updatePieChart(state)
        updateBarChart(state)
        categorySummaryAdapter?.submitList(state.expenseSummary)
    }

    private fun updatePieChart(state: ReportUiState) {
        if (state.expenseSummary.isEmpty()) {
            binding.pieChart.clear()
            binding.pieChart.invalidate()
            return
        }
        val entries: List<PieEntry> = state.expenseSummary.map { summary ->
            PieEntry(summary.totalAmount.toFloat(), summary.categoryName)
        }
        val colors: List<Int> = state.expenseSummary.map { summary ->
            try { Color.parseColor(summary.categoryColor) } catch (e: Exception) { Color.GRAY }
        }
        val dataSet = PieDataSet(entries, "").apply {
            this.colors = colors
            sliceSpace = 2f
            valueTextSize = 11f
            valueTextColor = Color.WHITE
        }
        binding.pieChart.data = PieData(dataSet).apply {
            setValueFormatter(PercentFormatter(binding.pieChart))
        }
        binding.pieChart.invalidate()
    }

    private fun updateBarChart(state: ReportUiState) {
        if (state.monthlyTrend.isEmpty()) {
            binding.barChart.clear()
            binding.barChart.invalidate()
            return
        }
        val incomeEntries = ArrayList<BarEntry>()
        val expenseEntries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()
        val monthNames = listOf("Jan","Feb","Mar","Apr","Mei","Jun","Jul","Agu","Sep","Okt","Nov","Des")

        state.monthlyTrend.forEachIndexed { index, trend ->
            incomeEntries.add(BarEntry(index.toFloat(), trend.totalIncome.toFloat()))
            expenseEntries.add(BarEntry(index.toFloat(), trend.totalExpense.toFloat()))
            val parts = trend.monthYear.split("-")
            if (parts.size == 2) {
                val mIdx = (parts[1].toIntOrNull() ?: 1) - 1
                labels.add("${monthNames.getOrElse(mIdx) { "" }} ${parts[0].takeLast(2)}")
            } else labels.add(trend.monthYear)
        }

        val groupSpace = 0.2f
        val barSpace = 0.02f
        val barWidth = 0.38f

        val incomeSet = BarDataSet(incomeEntries, "Pemasukan").apply { color = Color.parseColor("#4CAF50") }
        val expenseSet = BarDataSet(expenseEntries, "Pengeluaran").apply { color = Color.parseColor("#F44336") }
        val barData = BarData(incomeSet, expenseSet).apply { this.barWidth = barWidth }

        binding.barChart.apply {
            data = barData
            groupBars(0f, groupSpace, barSpace)
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(labels)
                setCenterAxisLabels(true)
                axisMinimum = 0f
                axisMaximum = barData.getGroupWidth(groupSpace, barSpace) * state.monthlyTrend.size
            }
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        categorySummaryAdapter = null
    }
}
