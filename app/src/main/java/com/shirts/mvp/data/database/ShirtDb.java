package com.shirts.mvp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.shirts.mvp.data.model.Shirts;


@Database(entities = Shirts.class, version = 1)
public abstract class ShirtDb extends RoomDatabase {

  public abstract ShirtDao shirtDao();
}
