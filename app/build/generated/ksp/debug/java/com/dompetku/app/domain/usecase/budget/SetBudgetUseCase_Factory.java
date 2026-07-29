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
public final class SetBudgetUseCase_Factory implements Factory<SetBudgetUseCase> {
  private final Provider<BudgetRepository> budgetRepositoryProvider;

  public SetBudgetUseCase_Factory(Provider<BudgetRepository> budgetRepositoryProvider) {
    this.budgetRepositoryProvider = budgetRepositoryProvider;
  }

  @Override
  public SetBudgetUseCase get() {
    return newInstance(budgetRepositoryProvider.get());
  }

  public static SetBudgetUseCase_Factory create(
      Provider<BudgetRepository> budgetRepositoryProvider) {
    return new SetBudgetUseCase_Factory(budgetRepositoryProvider);
  }

  public static SetBudgetUseCase newInstance(BudgetRepository budgetRepository) {
    return new SetBudgetUseCase(budgetRepository);
  }
}
