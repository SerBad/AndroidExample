package com.serbad.androidexample;

import com.serbad.androidexample.common.results.BeautyResults;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhoutingjie on 2016/12/12.
 */

public class HttpAPI {
    private static String baseUrl = "http://gank.io/api/";
    private Retrofit retrofit;

    public HttpAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public API getAPI() {
        return retrofit.create(API.class);
    }

    public interface API {
        @GET("data/福利/{num}/{page}")
        Call<BeautyResults> getBeauty(@Path("num")int num,@Path("page")int page);
    }
}
