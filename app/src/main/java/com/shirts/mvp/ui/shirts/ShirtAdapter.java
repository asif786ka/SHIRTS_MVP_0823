package com.shirts.mvp.ui.shirts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.bumptech.glide.Glide;
import com.shirts.mvp.R;
import com.shirts.mvp.data.model.Shirts;
import com.shirts.mvp.ui.base.BaseRecyclerViewAdapter;

import io.reactivex.annotations.NonNull;
import java.security.InvalidParameterException;
import java.util.List;


class ShirtAdapter extends BaseRecyclerViewAdapter<ShirtAdapter.ShirtViewHolder>{

  /*------ nested viewholder ----*/
  class ShirtViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.created_time_text)
    TextView userText;
    @BindView(R.id.imageView)
    ImageView createdTimeText;
    public ShirtViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
  /*------ nested viewholder ----*/

  private List<Shirts> shirtses;
  private Context context;

  public ShirtAdapter(@NonNull List<Shirts> shirtses, Context context) {
    this.shirtses = shirtses;
    this.context = context;
  }

  @Override
  public ShirtViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view = LayoutInflater
        .from(viewGroup.getContext())
        .inflate(R.layout.item_shirt, viewGroup, false);
    return new ShirtViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    super.onBindViewHolder(viewHolder, i);
    ShirtViewHolder vh = (ShirtViewHolder) viewHolder; //safe cast
    Shirts shirts = shirtses.get(i);
    vh.titleText.setText(shirts.getName());
    vh.userText.setText(shirts.getColour());
    Glide.with(context).load(shirts.getPicture()).into(vh.createdTimeText);
  }

  @Override
  public int getItemCount() {
    return shirtses.size();
  }

  /* Public API*/

  public void replaceData(List<Shirts> shirtses) {
    this.shirtses.clear();
    this.shirtses.addAll(shirtses);
    notifyDataSetChanged();
  }

  public Shirts getItem(int position) {
    if (position < 0 || position >= shirtses.size()) {
      throw new InvalidParameterException("Invalid item index");
    }
    return shirtses.get(position);
  }

  public void clearData() {
    shirtses.clear();
    notifyDataSetChanged();
  }
}
