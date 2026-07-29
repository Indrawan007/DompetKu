package com.dompetku.app.presentation.report;

import com.dompetku.app.domain.usecase.report.GenerateReportUseCase;
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
public final class ReportViewModel_Factory implements Factory<ReportViewModel> {
  private final Provider<GenerateReportUseCase> generateReportUseCaseProvider;

  public ReportViewModel_Factory(Provider<GenerateReportUseCase> generateReportUseCaseProvider) {
    this.generateReportUseCaseProvider = generateReportUseCaseProvider;
  }

  @Override
  public ReportViewModel get() {
    return newInstance(generateReportUseCaseProvider.get());
  }

  public static ReportViewModel_Factory create(
      Provider<GenerateReportUseCase> generateReportUseCaseProvider) {
    return new ReportViewModel_Factory(generateReportUseCaseProvider);
  }

  public static ReportViewModel newInstance(GenerateReportUseCase generateReportUseCase) {
    return new ReportViewModel(generateReportUseCase);
  }
}
