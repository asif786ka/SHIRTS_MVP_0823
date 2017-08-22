package com.shirts.mvp.data;

import com.shirts.mvp.data.repository.Local;
import com.shirts.mvp.data.repository.ShirtDataSource;
import com.shirts.mvp.data.repository.Remote;
import com.shirts.mvp.data.repository.local.ShirtLocalDataSource;
import com.shirts.mvp.data.repository.remote.ShirtRemoteDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;


@Module
public class ShirtRepositoryModule {

  @Provides
  @Local
  @Singleton
  public ShirtDataSource provideLocalDataSource(ShirtLocalDataSource shirtLocalDataSource) {
    return shirtLocalDataSource;
  }

  @Provides
  @Remote
  @Singleton
  public ShirtDataSource provideRemoteDataSource(ShirtRemoteDataSource shirtRemoteDataSource) {
    return shirtRemoteDataSource;
  }

}
