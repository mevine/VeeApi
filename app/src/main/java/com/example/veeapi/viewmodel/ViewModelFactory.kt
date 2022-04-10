package com.example.veeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.veeapi.repository.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModel(repository) as T
    }
}