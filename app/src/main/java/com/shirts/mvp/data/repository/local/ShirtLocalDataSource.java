package com.shirts.mvp.data.repository.local;

import com.shirts.mvp.data.database.ShirtDao;
import com.shirts.mvp.data.model.Shirts;
import com.shirts.mvp.data.repository.ShirtDataSource;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;


public class ShirtLocalDataSource implements ShirtDataSource {

  private ShirtDao shirtDao;

  @Inject
  public ShirtLocalDataSource(ShirtDao shirtDao) {
    this.shirtDao = shirtDao;
  }

  @Override
  public Flowable<List<Shirts>> loadShirts(boolean forceRemote) {
    return shirtDao.getAllQuestions();
  }

  @Override
  public void addShirt(Shirts shirts) {
    // Insert new one
    shirtDao.insert(shirts);
  }

  @Override
  public void clearData() {
    // Clear old data
    shirtDao.deleteAll();
  }
}
