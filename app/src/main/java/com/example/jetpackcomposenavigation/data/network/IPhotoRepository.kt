package com.example.jetpackcomposenavigation.data.network

import com.example.jetpackcomposenavigation.data.model.Photo

class IPhotoRepository(private val apiService: PhotoApiService): PhotoRepository {

    override suspend fun getPhotos(page:Int): List<Photo>{
        return apiService.getPhotos(page)
    }

}