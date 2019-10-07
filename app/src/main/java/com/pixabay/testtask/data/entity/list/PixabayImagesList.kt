package com.pixabay.testtask.data.entity.list

import com.pixabay.testtask.data.entity.PixabayImage

data class PixabayImagesList (
    val total : Int,
    val totalHits : Int,
    val hits : List<PixabayImage>
)