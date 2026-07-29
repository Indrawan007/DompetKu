package com.dompetku.app.domain.usecase.transaction;

import com.dompetku.app.domain.repository.AccountRepository;
import com.dompetku.app.domain.repository.BudgetRepository;
import com.dompetku.app.domain.repository.TransactionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class GetDashboardSummaryUseCase_Factory implements Factory<GetDashboardSummaryUseCase> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<AccountRepository> accountRepositoryProvider;

  private final Provider<BudgetRepository> budgetRepositoryProvider;

  public GetDashboardSummaryUseCase_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider,
      Provider<BudgetRepository> budgetRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.accountRepositoryProvider = accountRepositoryProvider;
    this.budgetRepositoryProvider = budgetRepositoryProvider;
  }

  @Override
  public GetDashboardSummaryUseCase get() {
    return newInstance(transactionRepositoryProvider.get(), accountRepositoryProvider.get(), budgetRepositoryProvider.get());
  }

  public static GetDashboardSummaryUseCase_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider,
      Provider<BudgetRepository> budgetRepositoryProvider) {
    return new GetDashboardSummaryUseCase_Factory(transactionRepositoryProvider, accountRepositoryProvider, budgetRepositoryProvider);
  }

  public static GetDashboardSummaryUseCase newInstance(TransactionRepository transactionRepository,
      AccountRepository accountRepository, BudgetRepository budgetRepository) {
    return new GetDashboardSummaryUseCase(transactionRepository, accountRepository, budgetRepository);
  }
}
