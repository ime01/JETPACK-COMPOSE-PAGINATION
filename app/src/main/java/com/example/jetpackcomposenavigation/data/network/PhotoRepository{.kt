package com.example.jetpackcomposenavigation.data.network

import com.example.jetpackcomposenavigation.data.model.Photo

interface PhotoRepository {
    suspend fun getPhotos(page:Int): List<Photo>
}