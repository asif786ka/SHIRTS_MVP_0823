package com.shirts.mvp.ui.shirts;

import com.shirts.mvp.data.ShirtRepositoryComponent;
import com.shirts.mvp.ui.base.ActivityScope;
import com.shirts.mvp.util.schedulers.SchedulerModule;
import dagger.Component;


@ActivityScope
@Component(modules = {ShirtsPresenterModule.class, SchedulerModule.class}, dependencies = {
    ShirtRepositoryComponent.class
})
public interface ShirtsComponent {
  void inject(ShirtsActivity shirtsActivity);
}
