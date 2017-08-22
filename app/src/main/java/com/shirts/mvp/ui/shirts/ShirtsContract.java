package com.shirts.mvp.ui.shirts;

import com.shirts.mvp.data.model.Shirts;
import com.shirts.mvp.ui.base.BasePresenter;
import java.util.List;

public interface ShirtsContract {
  interface View {
    void showShirts(List<Shirts> shirts);

    void clearShirts();

    void showNoDataMessage();

    void showErrorMessage(String error);

    void showShirtDetail(Shirts shirts);

    void stopLoadingIndicator();
  }

  interface Presenter extends BasePresenter<ShirtsContract.View> {
    void loadShirts(boolean onlineRequired);

    void getShirt(long shirtId);
  }
}
