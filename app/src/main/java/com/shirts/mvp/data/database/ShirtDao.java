package com.shirts.mvp.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.shirts.mvp.data.Config;
import com.shirts.mvp.data.model.Shirts;

import io.reactivex.Flowable;
import java.util.List;

/**
 * @author QuangNguyen (quangctkm9207).
 */
@Dao
public interface ShirtDao {
  @Query("SELECT * FROM " + Config.SHIRT_TABLE_NAME)
  Flowable<List<Shirts>> getAllQuestions();

  @Query("SELECT * FROM " + Config.SHIRT_TABLE_NAME + " WHERE id == :id")
  Flowable<Shirts> getQuestionById(int id);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Shirts shirts);

  @Query("DELETE FROM " + Config.SHIRT_TABLE_NAME)
  void deleteAll();
}
