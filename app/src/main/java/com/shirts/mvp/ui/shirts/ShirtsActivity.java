package com.shirts.mvp.ui.shirts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.shirts.mvp.R;
import com.shirts.mvp.data.model.Shirts;
import com.shirts.mvp.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ShirtsActivity extends BaseActivity implements ShirtsContract.View {

  @BindView(R.id.question_recycler)
  RecyclerView questionRecyclerView;
  @BindView(R.id.refresh)
  SwipeRefreshLayout refreshLayout;
  @BindView(R.id.notiText)
  TextView notiText;

  private ShirtAdapter adapter;
  @Inject
  ShirtsPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initializePresenter();
    setupWidgets();
  }

  private void initializePresenter() {
    DaggerShirtsComponent.builder()
        .shirtsPresenterModule(new ShirtsPresenterModule(this))
        .shirtRepositoryComponent(getShirtRepositoryComponent())
        .build()
        .inject(this);
  }

  private void setupWidgets() {
    // Setup recycler view
    adapter = new ShirtAdapter(new ArrayList<>(),this);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    questionRecyclerView.setLayoutManager(layoutManager);
    questionRecyclerView.setAdapter(adapter);
    questionRecyclerView.setItemAnimator(new DefaultItemAnimator());
    adapter.setOnItemClickListener(
        (view, position) -> presenter.getShirt(adapter.getItem(position).getId()));

    // Refresh layout
    refreshLayout.setOnRefreshListener(() -> presenter.loadShirts(true));
    // Set notification text visible first
    notiText.setVisibility(View.GONE);
  }

  @Override
  protected void onStart() {
    super.onStart();
    presenter.loadShirts(false);
  }

  @Override
  public void showShirts(List<Shirts> shirtses) {
    notiText.setVisibility(View.GONE);
    adapter.replaceData(shirtses);
  }

  @Override
  public void showNoDataMessage() {
    notiText.setVisibility(View.VISIBLE);
    notiText.setText(getResources().getString(R.string.error_no_data));
  }

  @Override
  public void showErrorMessage(String error) {
    notiText.setVisibility(View.VISIBLE);
    notiText.setText(error);
  }

  @Override
  public void clearShirts() {
    adapter.clearData();
  }

  @Override
  public void stopLoadingIndicator() {
    if (refreshLayout.isRefreshing()) {
      refreshLayout.setRefreshing(false);
    }
  }

  @Override
  public void showShirtDetail(Shirts shirts) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    //intent.setData(Uri.parse(shirts.getLink()));
    startActivity(intent);
  }
}
