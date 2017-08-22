package com.shirts.mvp.ui.shirts;

import dagger.Module;
import dagger.Provides;


@Module
public class ShirtsPresenterModule {
  private ShirtsContract.View view;

  public ShirtsPresenterModule(ShirtsContract.View view) {
    this.view = view;
  }

  @Provides
  public ShirtsContract.View provideView() {
    return view;
  }
}
