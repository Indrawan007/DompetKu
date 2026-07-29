package com.dompetku.app.domain.usecase.transaction;

import com.dompetku.app.domain.repository.AccountRepository;
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
public final class AddTransactionUseCase_Factory implements Factory<AddTransactionUseCase> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<AccountRepository> accountRepositoryProvider;

  public AddTransactionUseCase_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public AddTransactionUseCase get() {
    return newInstance(transactionRepositoryProvider.get(), accountRepositoryProvider.get());
  }

  public static AddTransactionUseCase_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    return new AddTransactionUseCase_Factory(transactionRepositoryProvider, accountRepositoryProvider);
  }

  public static AddTransactionUseCase newInstance(TransactionRepository transactionRepository,
      AccountRepository accountRepository) {
    return new AddTransactionUseCase(transactionRepository, accountRepository);
  }
}
