package com.example.jetpackcomposenavigation.data.network

import com.example.jetpackcomposenavigation.data.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {

    @GET("list")
    suspend fun getPhotos(@Query("page") pageNumber: Int, @Query("limit") limit: Int = 100): List<Photo>
}