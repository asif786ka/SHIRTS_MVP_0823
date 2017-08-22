package com.shirts.mvp;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.shirts.mvp.data.DaggerShirtRepositoryComponent;
import com.shirts.mvp.data.ShirtRepositoryComponent;
import com.squareup.leakcanary.LeakCanary;
import timber.log.Timber;


public class AndroidApplication extends Application {

  private ShirtRepositoryComponent repositoryComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    initializeDependencies();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      Stetho.initializeWithDefaults(this);
    }

    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }
    LeakCanary.install(this);
  }

  private void initializeDependencies() {
    repositoryComponent = DaggerShirtRepositoryComponent.builder()
        .appModule(new AppModule(this))
        .build();
  }

  public ShirtRepositoryComponent getShirtRepositoryComponent() {
    return repositoryComponent;
  }
}
