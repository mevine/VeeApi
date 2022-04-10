package com.example.veeapi.network

import com.example.veeapi.utils.Constants.Companion.Base_URL
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RetrofitInstance {
    /**
     * this is where we build our base URL and parse gson converter
     */
    private val retrofit by lazy {
            Retrofit
            .Builder().baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

//links repository with the interface
    val api:ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
}

}