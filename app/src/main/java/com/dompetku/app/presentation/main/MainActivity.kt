package com.dompetku.app.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dompetku.app.R
import com.dompetku.app.databinding.ActivityMainBinding
import com.dompetku.app.presentation.budget.SetBudgetBottomSheet
import com.dompetku.app.presentation.transaction.AddTransactionBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        setupFab()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        // Disable spacer item click
        binding.bottomNavigation.menu.findItem(R.id.nav_spacer)?.isEnabled = false

        // Show/Hide bottom bar + FAB
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.dashboardFragment,
                R.id.transactionListFragment,
                R.id.budgetFragment,
                R.id.reportFragment -> {
                    binding.bottomAppBar.visibility = View.VISIBLE
                    binding.bottomNavigation.visibility = View.VISIBLE
                    binding.fabAdd.show()
                }
                else -> {
                    binding.bottomAppBar.visibility = View.GONE
                    binding.bottomNavigation.visibility = View.GONE
                    binding.fabAdd.hide()
                }
            }
        }
    }

    private fun setupFab() {
        binding.fabAdd.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.budgetFragment -> {
                    SetBudgetBottomSheet.newInstance().show(
                        supportFragmentManager,
                        SetBudgetBottomSheet.TAG
                    )
                }
                else -> {
                    AddTransactionBottomSheet().show(
                        supportFragmentManager,
                        AddTransactionBottomSheet.TAG
                    )
                }
            }
        }
    }
}
