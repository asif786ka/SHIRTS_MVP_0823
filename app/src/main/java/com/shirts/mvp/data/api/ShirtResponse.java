package com.shirts.mvp.data.api;

import com.shirts.mvp.data.model.Shirts;
import java.util.List;

/**
 * @author QuangNguyen (quangctkm9207).
 */
public class ShirtResponse {
  private List<Shirts> shirtses;

  public List<Shirts> getShirtses() {
    return shirtses;
  }

  public void setShirtses(List<Shirts> shirtses) {
    this.shirtses = shirtses;
  }

}
