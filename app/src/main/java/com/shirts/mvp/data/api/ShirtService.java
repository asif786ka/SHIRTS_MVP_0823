package com.shirts.mvp.data.api;

import com.shirts.mvp.data.model.Shirts;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;


public interface ShirtService {
  @GET("shirts")
  Flowable<List<Shirts>> loadShirts();
}
