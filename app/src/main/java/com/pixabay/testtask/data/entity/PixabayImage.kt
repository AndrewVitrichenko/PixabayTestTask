package com.pixabay.testtask.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PixabayImage(
    val id: String? = null,
    val pageURL: String? = null,
    val type: String? = null,
    val tags: String? = null,
    val previewURL: String? = null,
    val previewWidth: Int = 0,
    val previewHeight: Int = 0,
    val webformatURL: String? = null,
    val webformatWidth: Int = 0,
    val webformatHeight: Int = 0,
    val largeImageURL: String? = null,
    val fullHDURL: String? = null,
    val imageURL: String? = null,
    val imageWidth: Int = 0,
    val imageHeight: Int = 0,
    val imageSize: Int = 0,
    val views: Int = 0,
    val downloads: Int = 0,
    val favorites: Int = 0,
    val likes: Int = 0,
    val comments: Int = 0,
    val userId: String? = null,
    val user: String? = null,
    val userImageURL: String? = null
) : Parcelable {

    fun getPreviewImage() : String {
        return previewURL ?: imageURL ?: fullHDURL ?: largeImageURL ?: ""
    }

    fun getImage() : String{
        return imageURL ?: fullHDURL ?: largeImageURL ?: webformatURL ?: ""
    }

    override fun equals(obj: Any?): Boolean {
        if (obj === this)
            return true
        val pixabayImage = obj as PixabayImage?
        return pixabayImage!!.id === this.id
    }
}


