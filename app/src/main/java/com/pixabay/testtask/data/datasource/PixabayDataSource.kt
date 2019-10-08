package com.pixabay.testtask.data.datasource

import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.data.entity.list.PixabayImagesList
import io.reactivex.Flowable
import io.reactivex.Single

interface PixabayDataSource {

    fun getImagesByText(text : String, page : Int) : Single<PixabayImagesList>

    fun getImageDetails(imageId : String) : Single<PixabayImage>
}