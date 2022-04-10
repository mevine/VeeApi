package com.example.veeapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veeapi.responsemodel.DataImage
import com.example.veeapi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(private var repository: Repository):ViewModel() {
    val myApiResponse: MutableLiveData<Response<DataImage>> = MutableLiveData()
    /**
     * we manage running tasks here
     * we use  coroutines for concurrency design pattern which sends api calls in a synchronous manner
     */
    fun fetchApiData(searchQuery : String){
        viewModelScope.launch {
            myApiResponse.value =  repository.fetchApiData(searchQuery)
            Log.d("resViewModel",repository.fetchApiData(searchQuery).toString())

        }
    }
}