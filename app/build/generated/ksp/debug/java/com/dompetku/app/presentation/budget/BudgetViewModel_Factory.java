package com.dompetku.app.presentation.budget;

import com.dompetku.app.domain.repository.CategoryRepository;
import com.dompetku.app.domain.usecase.budget.GetBudgetWithSpentUseCase;
import com.dompetku.app.domain.usecase.budget.SetBudgetUseCase;
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
public final class BudgetViewModel_Factory implements Factory<BudgetViewModel> {
  private final Provider<GetBudgetWithSpentUseCase> getBudgetWithSpentUseCaseProvider;

  private final Provider<SetBudgetUseCase> setBudgetUseCaseProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  public BudgetViewModel_Factory(
      Provider<GetBudgetWithSpentUseCase> getBudgetWithSpentUseCaseProvider,
      Provider<SetBudgetUseCase> setBudgetUseCaseProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    this.getBudgetWithSpentUseCaseProvider = getBudgetWithSpentUseCaseProvider;
    this.setBudgetUseCaseProvider = setBudgetUseCaseProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
  }

  @Override
  public BudgetViewModel get() {
    return newInstance(getBudgetWithSpentUseCaseProvider.get(), setBudgetUseCaseProvider.get(), categoryRepositoryProvider.get());
  }

  public static BudgetViewModel_Factory create(
      Provider<GetBudgetWithSpentUseCase> getBudgetWithSpentUseCaseProvider,
      Provider<SetBudgetUseCase> setBudgetUseCaseProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    return new BudgetViewModel_Factory(getBudgetWithSpentUseCaseProvider, setBudgetUseCaseProvider, categoryRepositoryProvider);
  }

  public static BudgetViewModel newInstance(GetBudgetWithSpentUseCase getBudgetWithSpentUseCase,
      SetBudgetUseCase setBudgetUseCase, CategoryRepository categoryRepository) {
    return new BudgetViewModel(getBudgetWithSpentUseCase, setBudgetUseCase, categoryRepository);
  }
}
