package com.dompetku.app.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dompetku.app.domain.model.Account
import com.dompetku.app.domain.model.Category
import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.AccountRepository
import com.dompetku.app.domain.repository.CategoryRepository
import com.dompetku.app.domain.usecase.transaction.AddTransactionUseCase
import com.dompetku.app.domain.usecase.transaction.DeleteTransactionUseCase
import com.dompetku.app.domain.usecase.transaction.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

// ── Events ─────────────────────────────────────────
sealed class TransactionEvent {
    data class Success(val message: String) : TransactionEvent()
    data class Error(val message: String) : TransactionEvent()
}

// ── ViewModel ──────────────────────────────────────
@OptIn(FlowPreview::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val categoryRepository: CategoryRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    // ── Search ─────────────────────────────────────
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // ── Transactions ───────────────────────────────
    val transactions: StateFlow<List<Transaction>> = _searchQuery
        .debounce(300L)
        .flatMapLatest { query: String ->
            if (query.isBlank()) getTransactionsUseCase.getAll()
            else getTransactionsUseCase.search(query)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    // ── Categories ─────────────────────────────────
    val expenseCategories: StateFlow<List<Category>> = categoryRepository
        .getByType(TransactionType.EXPENSE)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    val incomeCategories: StateFlow<List<Category>> = categoryRepository
        .getByType(TransactionType.INCOME)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    // ── Accounts ───────────────────────────────────
    val accounts: StateFlow<List<Account>> = accountRepository
        .getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    // ── Loading ────────────────────────────────────
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // ── Events ─────────────────────────────────────
    private val _event = MutableSharedFlow<TransactionEvent>()
    val event: SharedFlow<TransactionEvent> = _event.asSharedFlow()

    // ── Add ────────────────────────────────────────
    fun addTransaction(
        amount: Double,
        type: TransactionType,
        category: Category?,
        account: Account,
        note: String,
        date: Date
    ) {
        viewModelScope.launch {
            _isLoading.value = true

            val transaction = Transaction(
                amount = amount,
                type = type,
                categoryId = category?.id,
                categoryName = category?.name,
                categoryColor = category?.color,
                categoryIcon = category?.icon,
                accountId = account.id,
                accountName = account.name,
                note = note,
                date = date
            )

            when (val result = addTransactionUseCase(transaction)) {
                is AddTransactionUseCase.Result.Success ->
                    _event.emit(TransactionEvent.Success("Transaksi berhasil disimpan"))
                is AddTransactionUseCase.Result.Error ->
                    _event.emit(TransactionEvent.Error(result.message))
            }

            _isLoading.value = false
        }
    }

    // ── Delete ─────────────────────────────────────
    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            when (val result = deleteTransactionUseCase(transaction)) {
                is DeleteTransactionUseCase.Result.Success ->
                    _event.emit(TransactionEvent.Success("Transaksi dihapus"))
                is DeleteTransactionUseCase.Result.Error ->
                    _event.emit(TransactionEvent.Error(result.message))
            }
        }
    }

    // ── Search ─────────────────────────────────────
    fun setSearchQuery(query: String) {
        _searchQuery.update { query }
    }
}
