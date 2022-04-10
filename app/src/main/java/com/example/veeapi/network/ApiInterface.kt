package com.example.veeapi.network

import com.example.veeapi.responsemodel.DataImage
import com.example.veeapi.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    /**
     * Def Api call method
    this is where we parse Api parameters and queries
     */
    @GET("?key=${Constants.API_KEY}&q=")
    suspend fun fetchApiData(@Query("searchQuery") searchQuery:String):Response<DataImage>
}