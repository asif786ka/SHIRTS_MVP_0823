package com.shirts.mvp.data.repository;

import com.shirts.mvp.data.model.Shirts;

import io.reactivex.Flowable;
import java.util.List;


public interface ShirtDataSource {
  Flowable<List<Shirts>> loadShirts(boolean forceRemote);

  void addShirt(Shirts shirts);

  void clearData();
}
