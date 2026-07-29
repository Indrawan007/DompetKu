package com.dompetku.app.presentation.transaction;

import com.dompetku.app.domain.repository.AccountRepository;
import com.dompetku.app.domain.repository.CategoryRepository;
import com.dompetku.app.domain.usecase.transaction.AddTransactionUseCase;
import com.dompetku.app.domain.usecase.transaction.DeleteTransactionUseCase;
import com.dompetku.app.domain.usecase.transaction.GetTransactionsUseCase;
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
public final class TransactionViewModel_Factory implements Factory<TransactionViewModel> {
  private final Provider<GetTransactionsUseCase> getTransactionsUseCaseProvider;

  private final Provider<AddTransactionUseCase> addTransactionUseCaseProvider;

  private final Provider<DeleteTransactionUseCase> deleteTransactionUseCaseProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<AccountRepository> accountRepositoryProvider;

  public TransactionViewModel_Factory(
      Provider<GetTransactionsUseCase> getTransactionsUseCaseProvider,
      Provider<AddTransactionUseCase> addTransactionUseCaseProvider,
      Provider<DeleteTransactionUseCase> deleteTransactionUseCaseProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    this.getTransactionsUseCaseProvider = getTransactionsUseCaseProvider;
    this.addTransactionUseCaseProvider = addTransactionUseCaseProvider;
    this.deleteTransactionUseCaseProvider = deleteTransactionUseCaseProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public TransactionViewModel get() {
    return newInstance(getTransactionsUseCaseProvider.get(), addTransactionUseCaseProvider.get(), deleteTransactionUseCaseProvider.get(), categoryRepositoryProvider.get(), accountRepositoryProvider.get());
  }

  public static TransactionViewModel_Factory create(
      Provider<GetTransactionsUseCase> getTransactionsUseCaseProvider,
      Provider<AddTransactionUseCase> addTransactionUseCaseProvider,
      Provider<DeleteTransactionUseCase> deleteTransactionUseCaseProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    return new TransactionViewModel_Factory(getTransactionsUseCaseProvider, addTransactionUseCaseProvider, deleteTransactionUseCaseProvider, categoryRepositoryProvider, accountRepositoryProvider);
  }

  public static TransactionViewModel newInstance(GetTransactionsUseCase getTransactionsUseCase,
      AddTransactionUseCase addTransactionUseCase,
      DeleteTransactionUseCase deleteTransactionUseCase, CategoryRepository categoryRepository,
      AccountRepository accountRepository) {
    return new TransactionViewModel(getTransactionsUseCase, addTransactionUseCase, deleteTransactionUseCase, categoryRepository, accountRepository);
  }
}
