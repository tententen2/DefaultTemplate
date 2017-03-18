package com.tenjirawat.liveat500px.manager.http;

import com.tenjirawat.liveat500px.dao.PhotoItemCollectionDAO;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ten30 on 3/18/2017.
 */

public interface ApiService {
    @POST("list")
    Call<PhotoItemCollectionDAO> loadPhotoList();
}
