package com.vrlocal.android.baseproject.di;

import androidx.multidex.MultiDexApplication;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.internal.Beta;

@Beta
public abstract class DaggerApplication extends MultiDexApplication implements HasAndroidInjector {
  @Inject
  volatile DispatchingAndroidInjector<Object> androidInjector;

  @Override
  public void onCreate() {
    super.onCreate();
    injectIfNecessary();
  }

  protected abstract AndroidInjector<? extends DaggerApplication> applicationInjector();

  private void injectIfNecessary() {
    if (androidInjector == null) {
      synchronized (this) {
        if (androidInjector == null) {
          @SuppressWarnings("unchecked")
          AndroidInjector<DaggerApplication> applicationInjector =
              (AndroidInjector<DaggerApplication>) applicationInjector();
          applicationInjector.inject(this);
          if (androidInjector == null) {
            throw new IllegalStateException(
                "The AndroidInjector returned from applicationInjector() did not inject the "
                    + "DaggerApplication");
          }
        }
      }
    }
  }

  @Override
  public AndroidInjector<Object> androidInjector() {
    injectIfNecessary();

    return androidInjector;
  }
}
