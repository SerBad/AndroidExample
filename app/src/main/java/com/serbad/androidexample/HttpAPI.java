package com.serbad.androidexample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serbad.androidexample.common.StringConverterFactory;
import com.serbad.androidexample.common.results.BaseResult;
import com.serbad.androidexample.common.results.Beauty;
import com.serbad.androidexample.common.results.ResultDeserializer;

import java.text.SimpleDateFormat;

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
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting(); //格式化json
        gsonBuilder.serializeNulls();
        //处理results为空的时候，反序列化
        gsonBuilder.registerTypeAdapter(BaseResult.class, new ResultDeserializer());
        gsonBuilder.setDateFormat(SimpleDateFormat.FULL);
        Gson gson = gsonBuilder.create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public API getAPI() {
        return retrofit.create(API.class);
    }

    public interface API {
        @GET("data/福利/{num}/{page}")
        Call<BaseResult<Beauty>> getBeauty(@Path("num") int num, @Path("page") int page);
    }
}
