package com.pixabay.testtask.data

import com.pixabay.testtask.data.entity.PixabayImageApi
import com.pixabay.testtask.data.entity.list.PixabayImagesListApi
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    fun getImagesByText(
        @Query("key") key: String,
        @Query("q") text: String,
        @Query("page") page: Int,
        @Query("image_type") imageType: String = "photo")
            : Single<PixabayImagesListApi>

    @GET("api/")
    fun getImageDetails(
        @Query("key") key: String,
        @Query("id") imageId: String,
        @Query("image_type") imageType: String = "photo")
            : Single<PixabayImageApi>

}