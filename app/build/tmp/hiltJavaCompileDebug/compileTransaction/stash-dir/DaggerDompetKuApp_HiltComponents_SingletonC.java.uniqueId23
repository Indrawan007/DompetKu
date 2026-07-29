package com.dompetku.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.dompetku.app.data.local.AppDatabase;
import com.dompetku.app.data.local.dao.AccountDao;
import com.dompetku.app.data.local.dao.BudgetDao;
import com.dompetku.app.data.local.dao.CategoryDao;
import com.dompetku.app.data.local.dao.TransactionDao;
import com.dompetku.app.data.repository.AccountRepositoryImpl;
import com.dompetku.app.data.repository.BudgetRepositoryImpl;
import com.dompetku.app.data.repository.CategoryRepositoryImpl;
import com.dompetku.app.data.repository.TransactionRepositoryImpl;
import com.dompetku.app.di.AppModule_ProvidePreferencesManagerFactory;
import com.dompetku.app.di.DatabaseModule_ProvideAccountDaoFactory;
import com.dompetku.app.di.DatabaseModule_ProvideBudgetDaoFactory;
import com.dompetku.app.di.DatabaseModule_ProvideCategoryDaoFactory;
import com.dompetku.app.di.DatabaseModule_ProvideDatabaseFactory;
import com.dompetku.app.di.DatabaseModule_ProvideTransactionDaoFactory;
import com.dompetku.app.domain.usecase.budget.GetBudgetWithSpentUseCase;
import com.dompetku.app.domain.usecase.budget.SetBudgetUseCase;
import com.dompetku.app.domain.usecase.report.GenerateReportUseCase;
import com.dompetku.app.domain.usecase.transaction.AddTransactionUseCase;
import com.dompetku.app.domain.usecase.transaction.DeleteTransactionUseCase;
import com.dompetku.app.domain.usecase.transaction.GetDashboardSummaryUseCase;
import com.dompetku.app.domain.usecase.transaction.GetTransactionsUseCase;
import com.dompetku.app.presentation.budget.BudgetFragment;
import com.dompetku.app.presentation.budget.BudgetViewModel;
import com.dompetku.app.presentation.budget.BudgetViewModel_HiltModules_KeyModule_ProvideFactory;
import com.dompetku.app.presentation.budget.SetBudgetBottomSheet;
import com.dompetku.app.presentation.dashboard.DashboardFragment;
import com.dompetku.app.presentation.dashboard.DashboardViewModel;
import com.dompetku.app.presentation.dashboard.DashboardViewModel_HiltModules_KeyModule_ProvideFactory;
import com.dompetku.app.presentation.lock.LockActivity;
import com.dompetku.app.presentation.lock.LockViewModel;
import com.dompetku.app.presentation.lock.LockViewModel_HiltModules_KeyModule_ProvideFactory;
import com.dompetku.app.presentation.lock.SetupPinActivity;
import com.dompetku.app.presentation.main.MainActivity;
import com.dompetku.app.presentation.report.ReportFragment;
import com.dompetku.app.presentation.report.ReportViewModel;
import com.dompetku.app.presentation.report.ReportViewModel_HiltModules_KeyModule_ProvideFactory;
import com.dompetku.app.presentation.settings.SettingsFragment;
import com.dompetku.app.presentation.settings.SettingsViewModel;
import com.dompetku.app.presentation.settings.SettingsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.dompetku.app.presentation.splash.SplashActivity;
import com.dompetku.app.presentation.splash.SplashActivity_MembersInjector;
import com.dompetku.app.presentation.transaction.AddTransactionBottomSheet;
import com.dompetku.app.presentation.transaction.TransactionDetailFragment;
import com.dompetku.app.presentation.transaction.TransactionListFragment;
import com.dompetku.app.presentation.transaction.TransactionViewModel;
import com.dompetku.app.presentation.transaction.TransactionViewModel_HiltModules_KeyModule_ProvideFactory;
import com.dompetku.app.util.BackupManager;
import com.dompetku.app.util.PreferencesManager;
import com.dompetku.app.util.ResetManager;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerDompetKuApp_HiltComponents_SingletonC {
  private DaggerDompetKuApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public DompetKuApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements DompetKuApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public DompetKuApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements DompetKuApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public DompetKuApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements DompetKuApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public DompetKuApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements DompetKuApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public DompetKuApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements DompetKuApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public DompetKuApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements DompetKuApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public DompetKuApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements DompetKuApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public DompetKuApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends DompetKuApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends DompetKuApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectBudgetFragment(BudgetFragment budgetFragment) {
    }

    @Override
    public void injectSetBudgetBottomSheet(SetBudgetBottomSheet setBudgetBottomSheet) {
    }

    @Override
    public void injectDashboardFragment(DashboardFragment dashboardFragment) {
    }

    @Override
    public void injectReportFragment(ReportFragment reportFragment) {
    }

    @Override
    public void injectSettingsFragment(SettingsFragment settingsFragment) {
    }

    @Override
    public void injectAddTransactionBottomSheet(
        AddTransactionBottomSheet addTransactionBottomSheet) {
    }

    @Override
    public void injectTransactionDetailFragment(
        TransactionDetailFragment transactionDetailFragment) {
    }

    @Override
    public void injectTransactionListFragment(TransactionListFragment transactionListFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends DompetKuApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends DompetKuApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectLockActivity(LockActivity lockActivity) {
    }

    @Override
    public void injectSetupPinActivity(SetupPinActivity setupPinActivity) {
    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public void injectSplashActivity(SplashActivity splashActivity) {
      injectSplashActivity2(splashActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(6).add(BudgetViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DashboardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LockViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ReportViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SettingsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(TransactionViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private SplashActivity injectSplashActivity2(SplashActivity instance) {
      SplashActivity_MembersInjector.injectPreferencesManager(instance, singletonCImpl.providePreferencesManagerProvider.get());
      return instance;
    }
  }

  private static final class ViewModelCImpl extends DompetKuApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<BudgetViewModel> budgetViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<LockViewModel> lockViewModelProvider;

    private Provider<ReportViewModel> reportViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private Provider<TransactionViewModel> transactionViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private GetBudgetWithSpentUseCase getBudgetWithSpentUseCase() {
      return new GetBudgetWithSpentUseCase(singletonCImpl.budgetRepositoryImplProvider.get());
    }

    private SetBudgetUseCase setBudgetUseCase() {
      return new SetBudgetUseCase(singletonCImpl.budgetRepositoryImplProvider.get());
    }

    private GetDashboardSummaryUseCase getDashboardSummaryUseCase() {
      return new GetDashboardSummaryUseCase(singletonCImpl.transactionRepositoryImplProvider.get(), singletonCImpl.accountRepositoryImplProvider.get(), singletonCImpl.budgetRepositoryImplProvider.get());
    }

    private GenerateReportUseCase generateReportUseCase() {
      return new GenerateReportUseCase(singletonCImpl.transactionRepositoryImplProvider.get());
    }

    private GetTransactionsUseCase getTransactionsUseCase() {
      return new GetTransactionsUseCase(singletonCImpl.transactionRepositoryImplProvider.get());
    }

    private AddTransactionUseCase addTransactionUseCase() {
      return new AddTransactionUseCase(singletonCImpl.transactionRepositoryImplProvider.get(), singletonCImpl.accountRepositoryImplProvider.get());
    }

    private DeleteTransactionUseCase deleteTransactionUseCase() {
      return new DeleteTransactionUseCase(singletonCImpl.transactionRepositoryImplProvider.get(), singletonCImpl.accountRepositoryImplProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.budgetViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.lockViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.reportViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.transactionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(6).put("com.dompetku.app.presentation.budget.BudgetViewModel", ((Provider) budgetViewModelProvider)).put("com.dompetku.app.presentation.dashboard.DashboardViewModel", ((Provider) dashboardViewModelProvider)).put("com.dompetku.app.presentation.lock.LockViewModel", ((Provider) lockViewModelProvider)).put("com.dompetku.app.presentation.report.ReportViewModel", ((Provider) reportViewModelProvider)).put("com.dompetku.app.presentation.settings.SettingsViewModel", ((Provider) settingsViewModelProvider)).put("com.dompetku.app.presentation.transaction.TransactionViewModel", ((Provider) transactionViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.dompetku.app.presentation.budget.BudgetViewModel 
          return (T) new BudgetViewModel(viewModelCImpl.getBudgetWithSpentUseCase(), viewModelCImpl.setBudgetUseCase(), singletonCImpl.categoryRepositoryImplProvider.get());

          case 1: // com.dompetku.app.presentation.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(viewModelCImpl.getDashboardSummaryUseCase());

          case 2: // com.dompetku.app.presentation.lock.LockViewModel 
          return (T) new LockViewModel(singletonCImpl.providePreferencesManagerProvider.get());

          case 3: // com.dompetku.app.presentation.report.ReportViewModel 
          return (T) new ReportViewModel(viewModelCImpl.generateReportUseCase());

          case 4: // com.dompetku.app.presentation.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.providePreferencesManagerProvider.get(), singletonCImpl.backupManagerProvider.get(), singletonCImpl.resetManagerProvider.get());

          case 5: // com.dompetku.app.presentation.transaction.TransactionViewModel 
          return (T) new TransactionViewModel(viewModelCImpl.getTransactionsUseCase(), viewModelCImpl.addTransactionUseCase(), viewModelCImpl.deleteTransactionUseCase(), singletonCImpl.categoryRepositoryImplProvider.get(), singletonCImpl.accountRepositoryImplProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends DompetKuApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends DompetKuApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends DompetKuApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<PreferencesManager> providePreferencesManagerProvider;

    private Provider<AppDatabase> provideDatabaseProvider;

    private Provider<CategoryDao> provideCategoryDaoProvider;

    private Provider<AccountDao> provideAccountDaoProvider;

    private Provider<BudgetDao> provideBudgetDaoProvider;

    private Provider<BudgetRepositoryImpl> budgetRepositoryImplProvider;

    private Provider<CategoryRepositoryImpl> categoryRepositoryImplProvider;

    private Provider<TransactionDao> provideTransactionDaoProvider;

    private Provider<TransactionRepositoryImpl> transactionRepositoryImplProvider;

    private Provider<AccountRepositoryImpl> accountRepositoryImplProvider;

    private Provider<BackupManager> backupManagerProvider;

    private Provider<ResetManager> resetManagerProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.providePreferencesManagerProvider = DoubleCheck.provider(new SwitchingProvider<PreferencesManager>(singletonCImpl, 0));
      this.provideDatabaseProvider = new DelegateFactory<>();
      this.provideCategoryDaoProvider = DoubleCheck.provider(new SwitchingProvider<CategoryDao>(singletonCImpl, 4));
      this.provideAccountDaoProvider = DoubleCheck.provider(new SwitchingProvider<AccountDao>(singletonCImpl, 5));
      DelegateFactory.setDelegate(provideDatabaseProvider, DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 3)));
      this.provideBudgetDaoProvider = DoubleCheck.provider(new SwitchingProvider<BudgetDao>(singletonCImpl, 2));
      this.budgetRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<BudgetRepositoryImpl>(singletonCImpl, 1));
      this.categoryRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<CategoryRepositoryImpl>(singletonCImpl, 6));
      this.provideTransactionDaoProvider = DoubleCheck.provider(new SwitchingProvider<TransactionDao>(singletonCImpl, 8));
      this.transactionRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<TransactionRepositoryImpl>(singletonCImpl, 7));
      this.accountRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<AccountRepositoryImpl>(singletonCImpl, 9));
      this.backupManagerProvider = DoubleCheck.provider(new SwitchingProvider<BackupManager>(singletonCImpl, 10));
      this.resetManagerProvider = DoubleCheck.provider(new SwitchingProvider<ResetManager>(singletonCImpl, 11));
    }

    @Override
    public void injectDompetKuApp(DompetKuApp dompetKuApp) {
      injectDompetKuApp2(dompetKuApp);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private DompetKuApp injectDompetKuApp2(DompetKuApp instance) {
      DompetKuApp_MembersInjector.injectPreferencesManager(instance, providePreferencesManagerProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.dompetku.app.util.PreferencesManager 
          return (T) AppModule_ProvidePreferencesManagerFactory.providePreferencesManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 1: // com.dompetku.app.data.repository.BudgetRepositoryImpl 
          return (T) new BudgetRepositoryImpl(singletonCImpl.provideBudgetDaoProvider.get());

          case 2: // com.dompetku.app.data.local.dao.BudgetDao 
          return (T) DatabaseModule_ProvideBudgetDaoFactory.provideBudgetDao(singletonCImpl.provideDatabaseProvider.get());

          case 3: // com.dompetku.app.data.local.AppDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideCategoryDaoProvider, singletonCImpl.provideAccountDaoProvider);

          case 4: // com.dompetku.app.data.local.dao.CategoryDao 
          return (T) DatabaseModule_ProvideCategoryDaoFactory.provideCategoryDao(singletonCImpl.provideDatabaseProvider.get());

          case 5: // com.dompetku.app.data.local.dao.AccountDao 
          return (T) DatabaseModule_ProvideAccountDaoFactory.provideAccountDao(singletonCImpl.provideDatabaseProvider.get());

          case 6: // com.dompetku.app.data.repository.CategoryRepositoryImpl 
          return (T) new CategoryRepositoryImpl(singletonCImpl.provideCategoryDaoProvider.get());

          case 7: // com.dompetku.app.data.repository.TransactionRepositoryImpl 
          return (T) new TransactionRepositoryImpl(singletonCImpl.provideTransactionDaoProvider.get());

          case 8: // com.dompetku.app.data.local.dao.TransactionDao 
          return (T) DatabaseModule_ProvideTransactionDaoFactory.provideTransactionDao(singletonCImpl.provideDatabaseProvider.get());

          case 9: // com.dompetku.app.data.repository.AccountRepositoryImpl 
          return (T) new AccountRepositoryImpl(singletonCImpl.provideAccountDaoProvider.get());

          case 10: // com.dompetku.app.util.BackupManager 
          return (T) new BackupManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideDatabaseProvider.get());

          case 11: // com.dompetku.app.util.ResetManager 
          return (T) new ResetManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideDatabaseProvider.get(), singletonCImpl.providePreferencesManagerProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
