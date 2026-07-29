package com.dompetku.app.domain.usecase.budget;

import com.dompetku.app.domain.repository.BudgetRepository;
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
public final class GetBudgetWithSpentUseCase_Factory implements Factory<GetBudgetWithSpentUseCase> {
  private final Provider<BudgetRepository> budgetRepositoryProvider;

  public GetBudgetWithSpentUseCase_Factory(Provider<BudgetRepository> budgetRepositoryProvider) {
    this.budgetRepositoryProvider = budgetRepositoryProvider;
  }

  @Override
  public GetBudgetWithSpentUseCase get() {
    return newInstance(budgetRepositoryProvider.get());
  }

  public static GetBudgetWithSpentUseCase_Factory create(
      Provider<BudgetRepository> budgetRepositoryProvider) {
    return new GetBudgetWithSpentUseCase_Factory(budgetRepositoryProvider);
  }

  public static GetBudgetWithSpentUseCase newInstance(BudgetRepository budgetRepository) {
    return new GetBudgetWithSpentUseCase(budgetRepository);
  }
}
