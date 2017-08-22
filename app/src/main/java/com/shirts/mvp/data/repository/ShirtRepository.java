package com.shirts.mvp.data.repository;

import com.shirts.mvp.data.model.Shirts;

import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;


public class ShirtRepository implements ShirtDataSource {

  private ShirtDataSource remoteDataSource;
  private ShirtDataSource localDataSource;

  @Inject
  public ShirtRepository(@Local ShirtDataSource localDataSource,
                         @Remote ShirtDataSource remoteDataSource) {
    this.localDataSource = localDataSource;
    this.remoteDataSource = remoteDataSource;
  }

  @Override
  public Flowable<List<Shirts>> loadShirts(boolean forceRemote) {
    Flowable<List<Shirts>> questions;
    if (forceRemote) {
      questions = remoteDataSource.loadShirts(true).doOnEach(notification -> {
        // Save new data to local data source
        List<Shirts> list = notification.getValue();
        if (list != null && !list.isEmpty()) {
          saveDataToLocal(list);
        }
      });
    } else {
      questions =
          localDataSource.loadShirts(false);
    }
    return questions;
  }

  @Override
  public void addShirt(Shirts shirts) {
    //Currently, we do not need this.
    throw new UnsupportedOperationException("Unsupported operation");
  }

  @Override
  public void clearData() {
    //Currently, we do not need this.
    throw new UnsupportedOperationException("Unsupported operation");
  }

  // A helper method to save data in database after fetching new data from remote source.
  private void saveDataToLocal(List<Shirts> shirtses) {
    // Clear old data
    localDataSource.clearData();
    for (Shirts shirts : shirtses) {
      localDataSource.addShirt(shirts);
    }
  }
}
