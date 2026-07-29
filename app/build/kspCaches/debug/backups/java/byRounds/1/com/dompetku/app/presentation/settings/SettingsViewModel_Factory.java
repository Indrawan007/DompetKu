package com.dompetku.app.presentation.settings;

import com.dompetku.app.util.BackupManager;
import com.dompetku.app.util.PreferencesManager;
import com.dompetku.app.util.ResetManager;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<PreferencesManager> preferencesManagerProvider;

  private final Provider<BackupManager> backupManagerProvider;

  private final Provider<ResetManager> resetManagerProvider;

  public SettingsViewModel_Factory(Provider<PreferencesManager> preferencesManagerProvider,
      Provider<BackupManager> backupManagerProvider, Provider<ResetManager> resetManagerProvider) {
    this.preferencesManagerProvider = preferencesManagerProvider;
    this.backupManagerProvider = backupManagerProvider;
    this.resetManagerProvider = resetManagerProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(preferencesManagerProvider.get(), backupManagerProvider.get(), resetManagerProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<PreferencesManager> preferencesManagerProvider,
      Provider<BackupManager> backupManagerProvider, Provider<ResetManager> resetManagerProvider) {
    return new SettingsViewModel_Factory(preferencesManagerProvider, backupManagerProvider, resetManagerProvider);
  }

  public static SettingsViewModel newInstance(PreferencesManager preferencesManager,
      BackupManager backupManager, ResetManager resetManager) {
    return new SettingsViewModel(preferencesManager, backupManager, resetManager);
  }
}
