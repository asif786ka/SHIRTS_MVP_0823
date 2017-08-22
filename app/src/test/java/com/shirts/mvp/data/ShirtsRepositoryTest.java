package com.shirts.mvp.data;

import com.shirts.mvp.data.model.Shirts;
import com.shirts.mvp.data.repository.Local;
import com.shirts.mvp.data.repository.ShirtDataSource;
import com.shirts.mvp.data.repository.ShirtRepository;
import com.shirts.mvp.data.repository.Remote;

import io.reactivex.Flowable;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShirtsRepositoryTest {

  private static final Shirts SHIRTS_1 = new Shirts(1,"test","red",89);
  private static final Shirts SHIRTS_2 = new Shirts(2,"test","orange",91);
  private static final Shirts SHIRTS_3 = new Shirts(3,"test","green",92);
  private List<Shirts> shirts = Arrays.asList(SHIRTS_1, SHIRTS_2, SHIRTS_3);

  @Mock
  @Local
  private ShirtDataSource localDataSource;

  @Mock
  @Remote
  private ShirtDataSource remoteDataSource;

  private ShirtRepository repository;

  @Before
  public void setup() {
    repository = new ShirtRepository(localDataSource, remoteDataSource);
  }

  @Test
  public void loadShirts_FromLocal() {
    repository.loadShirts(false);

    verify(localDataSource).loadShirts(false);
    verify(remoteDataSource, never()).loadShirts(true);
  }

  @Test
  public void loadShirts_FromRemote() {
    doReturn(Flowable.just(shirts)).when(remoteDataSource).loadShirts(true);
    repository.loadShirts(true);

    verify(remoteDataSource).loadShirts(true);
    verify(localDataSource, never()).loadShirts(false);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void addShirt_ShouldThrowException() {
    repository.addShirt(SHIRTS_1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void clearData_ShouldThrowException() {
    repository.clearData();
  }
}
