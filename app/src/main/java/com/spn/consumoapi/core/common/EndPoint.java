package com.spn.consumoapi.core.common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EndPoint {
    public static final String END_POINT = "http://jsonplaceholder.typicode.com/";
    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(EndPoint.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
