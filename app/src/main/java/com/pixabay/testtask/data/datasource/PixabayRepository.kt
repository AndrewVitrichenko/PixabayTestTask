package com.pixabay.testtask.data.datasource

import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.data.entity.list.PixabayImagesList
import io.reactivex.Single
import javax.inject.Inject

class PixabayRepository @Inject constructor(private val remoteDataSource: PixabayRemoteDataSource) : PixabayDataSource {
    override fun getImagesByText(text: String, page: Int): Single<PixabayImagesList> {
        return remoteDataSource.getImagesByText(text, page)
    }

    override fun getImageDetails(imageId: String): Single<PixabayImage> {
        return remoteDataSource.getImageDetails(imageId)
    }
}