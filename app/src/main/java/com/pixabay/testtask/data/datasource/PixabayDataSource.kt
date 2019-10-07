package com.pixabay.testtask.data.datasource

import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.data.entity.list.PixabayImagesList
import io.reactivex.Flowable

interface PixabayDataSource {

    fun getImagesByText(text : String) : Flowable<PixabayImagesList>

    fun getImageDetails(imageId : String) : Flowable<PixabayImage>
}