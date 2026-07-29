package com.dompetku.app.domain.usecase.transaction;

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
public final class GetTransactionsUseCase_Factory implements Factory<GetTransactionsUseCase> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public GetTransactionsUseCase_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public GetTransactionsUseCase get() {
    return newInstance(transactionRepositoryProvider.get());
  }

  public static GetTransactionsUseCase_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new GetTransactionsUseCase_Factory(transactionRepositoryProvider);
  }

  public static GetTransactionsUseCase newInstance(TransactionRepository transactionRepository) {
    return new GetTransactionsUseCase(transactionRepository);
  }
}
