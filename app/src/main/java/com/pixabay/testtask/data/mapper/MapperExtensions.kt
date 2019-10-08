package com.pixabay.testtask.data.mapper

import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.data.entity.PixabayImageApi
import com.pixabay.testtask.data.entity.list.PixabayImagesList
import com.pixabay.testtask.data.entity.list.PixabayImagesListApi


fun PixabayImagesListApi.toPixabayImagesList() : PixabayImagesList{
    val images = hits.map { pixabayImageApi -> pixabayImageApi.toPixabayImage() }
    return PixabayImagesList(total,totalHits,images)
}

fun PixabayImageApi.toPixabayImage() : PixabayImage{
    return PixabayImage(
        id = id ?: "N/A",
        pageURL = pageURL ?: "N/A" ,
        type = type ?: "N/A",
        tags = tags ?: "N/A",
        previewURL = previewURL ?: "N/A",
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        webformatURL = webformatURL ?: "N/A",
        webformatWidth = webformatWidth,
        webformatHeight = webformatHeight,
        largeImageURL = largeImageURL ?: "N/A",
        fullHDURL = fullHDURL ?: "N/A",
        imageURL = imageURL ?: "N/A",
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        imageSize = imageSize,
        views = views,
        downloads = downloads,
        favorites = favorites,
        likes = likes,
        comments = comments,
        userId = userId ?: "N/A",
        user = user ?: "N/A",
        userImageURL = userImageURL ?: "N/A"
    )
}