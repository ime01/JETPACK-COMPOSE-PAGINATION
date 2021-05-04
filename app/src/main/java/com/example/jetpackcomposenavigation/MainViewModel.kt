package com.example.jetpackcomposenavigation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpackcomposenavigation.data.model.Photo
import com.example.jetpackcomposenavigation.data.network.PhotoRepository
import com.example.jetpackcomposenavigation.data.pagingdatasource.PhotoSource
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val photoRepository: PhotoRepository) : ViewModel(){


    fun getPhotoPagination(): Flow<PagingData<Photo>>{
        return Pager(PagingConfig(pageSize = 20)){
            PhotoSource(photoRepository)
        }.flow
    }
}