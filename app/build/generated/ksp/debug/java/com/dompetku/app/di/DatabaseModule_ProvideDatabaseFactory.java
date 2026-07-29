package com.dompetku.app.di;

import android.content.Context;
import com.dompetku.app.data.local.AppDatabase;
import com.dompetku.app.data.local.dao.AccountDao;
import com.dompetku.app.data.local.dao.CategoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideDatabaseFactory implements Factory<AppDatabase> {
  private final Provider<Context> contextProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<AccountDao> accountDaoProvider;

  public DatabaseModule_ProvideDatabaseFactory(Provider<Context> contextProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<AccountDao> accountDaoProvider) {
    this.contextProvider = contextProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.accountDaoProvider = accountDaoProvider;
  }

  @Override
  public AppDatabase get() {
    return provideDatabase(contextProvider.get(), categoryDaoProvider, accountDaoProvider);
  }

  public static DatabaseModule_ProvideDatabaseFactory create(Provider<Context> contextProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<AccountDao> accountDaoProvider) {
    return new DatabaseModule_ProvideDatabaseFactory(contextProvider, categoryDaoProvider, accountDaoProvider);
  }

  public static AppDatabase provideDatabase(Context context,
      Provider<CategoryDao> categoryDaoProvider, Provider<AccountDao> accountDaoProvider) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDatabase(context, categoryDaoProvider, accountDaoProvider));
  }
}
