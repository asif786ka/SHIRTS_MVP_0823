package com.shirts.mvp.ui;

import com.shirts.mvp.data.model.Shirts;
import com.shirts.mvp.data.repository.ShirtRepository;
import com.shirts.mvp.ui.shirts.ShirtsContract;
import com.shirts.mvp.ui.shirts.ShirtsPresenter;
import com.shirts.mvp.util.schedulers.RunOn;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.shirts.mvp.util.schedulers.SchedulerType.COMPUTATION;
import static com.shirts.mvp.util.schedulers.SchedulerType.UI;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShirtsPresenterTest {

  private static final Shirts SHIRTS_1 = new Shirts(1,"test","red",89);
  private static final Shirts SHIRTS_2 = new Shirts(2,"test","orange",91);
  private static final Shirts SHIRTS_3 = new Shirts(3,"test","green",92);
  private List<Shirts> shirts = Arrays.asList(SHIRTS_1, SHIRTS_2, SHIRTS_3);

  @Mock
  private ShirtRepository repository;

  @Mock
  private ShirtsContract.View view;

  @RunOn(COMPUTATION)
  private Scheduler computationScheduler;

  @RunOn(UI)
  private Scheduler uiScheduler;

  private TestScheduler testScheduler;

  private ShirtsPresenter presenter;

  @Before
  public void setUp() {
    // Make sure to use TestScheduler for RxJava testing
    testScheduler = new TestScheduler();
    computationScheduler = testScheduler;
    uiScheduler = testScheduler;
    presenter = new ShirtsPresenter(repository, view, computationScheduler, uiScheduler);
  }

  @Test
  public void loadShirts_FromRepoToView_WithDataReturned() {
    doReturn(Flowable.just(shirts)).when(repository).loadShirts(true);
    presenter.loadShirts(true);
    testScheduler.triggerActions(); // Trigger actions for test scheduler

    verify(view).clearShirts();
    verify(view).showShirts(shirts);
    verify(view, atLeastOnce()).stopLoadingIndicator();
  }

  @Test
  public void loadShirts_FromRepoToView_WithNoDataReturned() {
    doReturn(Flowable.just(new ArrayList<Shirts>())).when(repository).loadShirts(true);
    presenter.loadShirts(true);
    testScheduler.triggerActions(); // Trigger actions for test scheduler

    verify(view).clearShirts();
    verify(view, never()).showShirts(shirts);
    verify(view).showNoDataMessage();
    verify(view, atLeastOnce()).stopLoadingIndicator();
  }
}
