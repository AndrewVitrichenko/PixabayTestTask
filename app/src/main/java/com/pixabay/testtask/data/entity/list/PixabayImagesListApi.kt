package com.pixabay.testtask.data.entity.list

import com.google.gson.annotations.SerializedName
import com.pixabay.testtask.data.entity.PixabayImageApi

data class PixabayImagesListApi(

    @SerializedName("total")
    val total : Int,

    @SerializedName("totalHits")
    val totalHits : Int,

    @SerializedName("hits")
    val hits : List<PixabayImageApi>
)