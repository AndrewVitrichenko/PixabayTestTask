package com.pixabay.testtask.data.datasource

import com.pixabay.testtask.BuildConfig
import com.pixabay.testtask.data.PixabayApi
import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.data.entity.PixabayImageApi
import com.pixabay.testtask.data.entity.list.PixabayImagesList
import com.pixabay.testtask.data.entity.list.PixabayImagesListApi
import com.pixabay.testtask.data.mapper.toPixabayImage
import com.pixabay.testtask.data.mapper.toPixabayImagesList
import io.reactivex.Single
import javax.inject.Inject

class PixabayRemoteDataSource @Inject constructor(private val pixabayApi: PixabayApi) : PixabayDataSource {
    override fun getImagesByText(text: String, page: Int): Single<PixabayImagesList> {
        return pixabayApi.getImagesByText(BuildConfig.API_KEY,text, page)
            .map { pixabayImageList: PixabayImagesListApi -> pixabayImageList.toPixabayImagesList() }
    }

    override fun getImageDetails(imageId: String): Single<PixabayImage> {
        return pixabayApi.getImageDetails(BuildConfig.API_KEY,imageId)
            .map { pixabayImageApi: PixabayImageApi -> pixabayImageApi.toPixabayImage() }
    }
}