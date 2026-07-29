package com.dompetku.app.presentation.dashboard;

import com.dompetku.app.domain.usecase.transaction.GetDashboardSummaryUseCase;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<GetDashboardSummaryUseCase> getDashboardSummaryUseCaseProvider;

  public DashboardViewModel_Factory(
      Provider<GetDashboardSummaryUseCase> getDashboardSummaryUseCaseProvider) {
    this.getDashboardSummaryUseCaseProvider = getDashboardSummaryUseCaseProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(getDashboardSummaryUseCaseProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<GetDashboardSummaryUseCase> getDashboardSummaryUseCaseProvider) {
    return new DashboardViewModel_Factory(getDashboardSummaryUseCaseProvider);
  }

  public static DashboardViewModel newInstance(
      GetDashboardSummaryUseCase getDashboardSummaryUseCase) {
    return new DashboardViewModel(getDashboardSummaryUseCase);
  }
}
