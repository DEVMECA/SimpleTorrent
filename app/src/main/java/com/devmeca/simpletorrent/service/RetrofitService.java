package com.devmeca.simpletorrent.service;

import com.devmeca.simpletorrent.core.model.data.RetrofitResultDto;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("/ajaxApiCall")
    Call<RetrofitResultDto> getPosts(@Body RequestBody body);
}
