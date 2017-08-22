package com.shirts.mvp.data;

import com.shirts.mvp.data.repository.ShirtRepository;
import com.shirts.mvp.AppModule;
import dagger.Component;
import javax.inject.Singleton;


@Singleton
@Component(modules = { ShirtRepositoryModule.class, AppModule.class, ApiServiceModule.class,
    DatabaseModule.class})
public interface ShirtRepositoryComponent {
  ShirtRepository provideShirtRepository();
}
