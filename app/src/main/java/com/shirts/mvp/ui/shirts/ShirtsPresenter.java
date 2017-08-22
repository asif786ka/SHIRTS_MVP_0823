package com.shirts.mvp.ui.shirts;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import com.shirts.mvp.data.model.Shirts;
import com.shirts.mvp.data.repository.ShirtRepository;
import com.shirts.mvp.util.schedulers.RunOn;
import io.reactivex.Scheduler;
import java.util.List;
import javax.inject.Inject;

import static com.shirts.mvp.util.schedulers.SchedulerType.*;

/**
 * A presenter with life-cycle aware.
 *
 */
public class ShirtsPresenter implements ShirtsContract.Presenter, LifecycleObserver {

  private ShirtRepository repository;

  private ShirtsContract.View view;

  private List<Shirts> caches;

  private Scheduler computationScheduler;
  private Scheduler uiScheduler;

  @Inject
  public ShirtsPresenter(ShirtRepository repository, ShirtsContract.View view,
                         @RunOn(COMPUTATION) Scheduler computationScheduler, @RunOn(UI) Scheduler uiScheduler) {
    this.repository = repository;
    this.view = view;
    this.computationScheduler = computationScheduler;
    this.uiScheduler = uiScheduler;
    // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
    if (view instanceof LifecycleOwner) {
      ((LifecycleOwner) view).getLifecycle().addObserver(this);
    }
  }

  @Override
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  public void onAttach() {

  }

  @Override
  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  public void onDetach() {
    // Clean up your resources here
  }

  @Override
  public void loadShirts(boolean onlineRequired) {
    // Clear old data on view
    view.clearShirts();
    // Load new one and populate it into view
    repository.loadShirts(onlineRequired)
        .subscribeOn(computationScheduler)
        .observeOn(uiScheduler)
        .subscribe(list -> handleReturnedData(list, onlineRequired),
            error -> handleError(error),
            () -> view.stopLoadingIndicator());
  }

  @Override
  public void getShirt(long shirtId) {
    // Load question detail from cache
    if (caches != null && caches.size() != 0) {
      for (Shirts shirts : caches) {
        if (shirts.getId() == shirtId) {
          view.showShirtDetail(shirts);
          break;
        }
      }
    }
  }

  /** Private helper methods go here**/

  /**
   * Handles the logic when receiving data from repository.
   * @param list
   * @param onlineRequired
   */
  private void handleReturnedData(List<Shirts> list, boolean onlineRequired) {
    view.stopLoadingIndicator();
    // Show on view if returned data is not empty.
    if (list != null && !list.isEmpty()) {
      view.showShirts(list);
      caches = list;
    } else {
      // if user requests from local storage and it turns out empty,
      // load again data from remote instead.
      if (!onlineRequired) {
        loadShirts(true);
      } else {
        view.showNoDataMessage();
      }
    }
  }

  /**
   * Handle error after loading data from repository.
   * @param error
   */
  private void handleError(Throwable error) {
    view.stopLoadingIndicator();
    view.showErrorMessage(error.getLocalizedMessage());
  }
}
