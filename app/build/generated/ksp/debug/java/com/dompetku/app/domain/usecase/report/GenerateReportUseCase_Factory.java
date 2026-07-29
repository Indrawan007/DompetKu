package com.dompetku.app.domain.usecase.report;

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
public final class GenerateReportUseCase_Factory implements Factory<GenerateReportUseCase> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public GenerateReportUseCase_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public GenerateReportUseCase get() {
    return newInstance(transactionRepositoryProvider.get());
  }

  public static GenerateReportUseCase_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new GenerateReportUseCase_Factory(transactionRepositoryProvider);
  }

  public static GenerateReportUseCase newInstance(TransactionRepository transactionRepository) {
    return new GenerateReportUseCase(transactionRepository);
  }
}
