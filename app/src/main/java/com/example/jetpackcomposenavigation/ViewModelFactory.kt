package com.example.jetpackcomposenavigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposenavigation.data.network.PhotoRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val photoRepository: PhotoRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(photoRepository) as T
        }
        return MainViewModel(photoRepository) as T
    }
}