package com.example.omaradel.productviewer;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  Service {

    @GET("/wp-content/uploads/2012/09/featured.txt")
    Call<JsonArray> readProductArray();
}
