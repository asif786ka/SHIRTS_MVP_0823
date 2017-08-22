package com.shirts.mvp.data.repository.remote;

import com.shirts.mvp.data.api.ShirtService;
import com.shirts.mvp.data.model.Shirts;
import com.shirts.mvp.data.repository.ShirtDataSource;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;


public class ShirtRemoteDataSource implements ShirtDataSource {

  private ShirtService shirtService;

  @Inject
  public ShirtRemoteDataSource(ShirtService shirtService) {
    this.shirtService = shirtService;
  }

  @Override
  public Flowable<List<Shirts>> loadShirts(boolean forceRemote) {
    return shirtService.loadShirts();
  }

  @Override
  public void addShirt(Shirts shirts) {
    //Currently, we do not need this for remote source.
    throw new UnsupportedOperationException("Unsupported operation");
  }

  @Override
  public void clearData() {
    //Currently, we do not need this for remote source.
    throw new UnsupportedOperationException("Unsupported operation");
  }
}
