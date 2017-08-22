package com.shirts.mvp.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.shirts.mvp.data.database.ShirtDao;
import com.shirts.mvp.data.database.ShirtDb;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;



@Module
public class DatabaseModule {
  private static final String DATABASE = "database_name";

  @Provides
  @Named(DATABASE)
  String provideDatabaseName() {
    return Config.DATABASE_NAME;
  }

  @Provides
  @Singleton
  ShirtDb provideShirtDatabaseDao(Context context, @Named(DATABASE) String databaseName) {
    return Room.databaseBuilder(context, ShirtDb.class, databaseName).build();
  }

  @Provides
  @Singleton
  ShirtDao provideShirtDao(ShirtDb shirtDb) {
    return shirtDb.shirtDao();
  }
}
