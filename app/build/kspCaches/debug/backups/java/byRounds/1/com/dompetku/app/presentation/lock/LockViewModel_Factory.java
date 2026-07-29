package com.dompetku.app.presentation.lock;

import com.dompetku.app.util.PreferencesManager;
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
public final class LockViewModel_Factory implements Factory<LockViewModel> {
  private final Provider<PreferencesManager> preferencesManagerProvider;

  public LockViewModel_Factory(Provider<PreferencesManager> preferencesManagerProvider) {
    this.preferencesManagerProvider = preferencesManagerProvider;
  }

  @Override
  public LockViewModel get() {
    return newInstance(preferencesManagerProvider.get());
  }

  public static LockViewModel_Factory create(
      Provider<PreferencesManager> preferencesManagerProvider) {
    return new LockViewModel_Factory(preferencesManagerProvider);
  }

  public static LockViewModel newInstance(PreferencesManager preferencesManager) {
    return new LockViewModel(preferencesManager);
  }
}
