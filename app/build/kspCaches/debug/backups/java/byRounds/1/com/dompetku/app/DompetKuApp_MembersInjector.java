package com.dompetku.app;

import com.dompetku.app.util.PreferencesManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DompetKuApp_MembersInjector implements MembersInjector<DompetKuApp> {
  private final Provider<PreferencesManager> preferencesManagerProvider;

  public DompetKuApp_MembersInjector(Provider<PreferencesManager> preferencesManagerProvider) {
    this.preferencesManagerProvider = preferencesManagerProvider;
  }

  public static MembersInjector<DompetKuApp> create(
      Provider<PreferencesManager> preferencesManagerProvider) {
    return new DompetKuApp_MembersInjector(preferencesManagerProvider);
  }

  @Override
  public void injectMembers(DompetKuApp instance) {
    injectPreferencesManager(instance, preferencesManagerProvider.get());
  }

  @InjectedFieldSignature("com.dompetku.app.DompetKuApp.preferencesManager")
  public static void injectPreferencesManager(DompetKuApp instance,
      PreferencesManager preferencesManager) {
    instance.preferencesManager = preferencesManager;
  }
}
