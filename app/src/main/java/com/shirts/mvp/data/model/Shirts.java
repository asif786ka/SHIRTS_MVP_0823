package com.shirts.mvp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shirts.mvp.data.Config;

/**
 * @author QuangNguyen (quangctkm9207).
 */
@Entity(tableName = Config.SHIRT_TABLE_NAME)
public class Shirts {

  @PrimaryKey
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("price")
  @Expose
  private Integer price;
  @SerializedName("picture")
  @Expose
  private String picture;
  @SerializedName("colour")
  @Expose
  private String colour;
  @SerializedName("size")
  @Expose
  private String size;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("quantity")
  @Expose
  private Integer quantity;


  public Shirts(int id, String picture, String colour, int price) {
    this.id = id;
    this.picture = picture;
    this.colour = colour;
    this.price = price;

  }
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
