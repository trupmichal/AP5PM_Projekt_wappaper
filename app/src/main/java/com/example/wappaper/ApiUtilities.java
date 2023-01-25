package com.example.wappaper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit=null;
    public static final String API="wvM34kGWfrkAXjvlsujqQZAIX20InI0EIsEesMagxrMqhriLq0oZjh06";

    public static ApiInterface getApiInterface()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }

}
