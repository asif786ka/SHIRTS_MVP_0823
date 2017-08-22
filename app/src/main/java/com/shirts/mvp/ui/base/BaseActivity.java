package com.shirts.mvp.ui.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v7.app.AppCompatActivity;
import com.shirts.mvp.AndroidApplication;
import com.shirts.mvp.data.ShirtRepositoryComponent;


public class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {
  private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

  protected void addFragment(int containerViewId, Fragment fragment) {
    final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
    fragmentTransaction.add(containerViewId, fragment);
    fragmentTransaction.commit();
  }

  protected ShirtRepositoryComponent getShirtRepositoryComponent() {
    return ((AndroidApplication) getApplication()).getShirtRepositoryComponent();
  }

  @Override
  public LifecycleRegistry getLifecycle() {
    return lifecycleRegistry;
  }
}
