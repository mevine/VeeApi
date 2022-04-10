package com.example.veeapi.repository

import android.util.Log
import com.example.veeapi.responsemodel.DataImage
import com.example.veeapi.network.RetrofitInstance
import retrofit2.Response

class Repository {
    /**
     * communicates search query to interface and feed the Api response to viewModel
     * to enable this to work we link it to the interface
     */
    suspend fun fetchApiData(searchQuery:String):Response<DataImage>{
        Log.d("resRepository",RetrofitInstance.api.fetchApiData(searchQuery).toString())
        return RetrofitInstance.api.fetchApiData(searchQuery)

    }
}