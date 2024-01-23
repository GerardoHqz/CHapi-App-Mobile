package com.tde.chapi.client

import com.tde.chapi.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getRetrofit(): Retrofit{

        val logger = HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build();

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://apichapi.me/")
            .client(client)
            .build()

        return retrofit;
    }

    fun getApiServices(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}