package com.dompetku.app.util;

import android.content.Context;
import com.dompetku.app.data.local.AppDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ResetManager_Factory implements Factory<ResetManager> {
  private final Provider<Context> contextProvider;

  private final Provider<AppDatabase> databaseProvider;

  private final Provider<PreferencesManager> preferencesManagerProvider;

  public ResetManager_Factory(Provider<Context> contextProvider,
      Provider<AppDatabase> databaseProvider,
      Provider<PreferencesManager> preferencesManagerProvider) {
    this.contextProvider = contextProvider;
    this.databaseProvider = databaseProvider;
    this.preferencesManagerProvider = preferencesManagerProvider;
  }

  @Override
  public ResetManager get() {
    return newInstance(contextProvider.get(), databaseProvider.get(), preferencesManagerProvider.get());
  }

  public static ResetManager_Factory create(Provider<Context> contextProvider,
      Provider<AppDatabase> databaseProvider,
      Provider<PreferencesManager> preferencesManagerProvider) {
    return new ResetManager_Factory(contextProvider, databaseProvider, preferencesManagerProvider);
  }

  public static ResetManager newInstance(Context context, AppDatabase database,
      PreferencesManager preferencesManager) {
    return new ResetManager(context, database, preferencesManager);
  }
}
