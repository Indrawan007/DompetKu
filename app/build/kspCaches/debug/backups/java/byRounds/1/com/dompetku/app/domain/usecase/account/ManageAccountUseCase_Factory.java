package com.dompetku.app.domain.usecase.account;

import com.dompetku.app.domain.repository.AccountRepository;
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
public final class ManageAccountUseCase_Factory implements Factory<ManageAccountUseCase> {
  private final Provider<AccountRepository> accountRepositoryProvider;

  public ManageAccountUseCase_Factory(Provider<AccountRepository> accountRepositoryProvider) {
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public ManageAccountUseCase get() {
    return newInstance(accountRepositoryProvider.get());
  }

  public static ManageAccountUseCase_Factory create(
      Provider<AccountRepository> accountRepositoryProvider) {
    return new ManageAccountUseCase_Factory(accountRepositoryProvider);
  }

  public static ManageAccountUseCase newInstance(AccountRepository accountRepository) {
    return new ManageAccountUseCase(accountRepository);
  }
}
