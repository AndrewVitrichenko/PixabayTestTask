package com.pixabay.testtask.data.entity

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil


data class PixabayImage(
    val id: String?,
    val pageURL: String?,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String?,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageURL: String?,
    val fullHDURL: String?,
    val imageURL: String?,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val favorites: Int,
    val likes: Int,
    val comments: Int,
    val userId: String?,
    val user: String?,
    val userImageURL: String?
){

    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<PixabayImage> = object : DiffUtil.ItemCallback<PixabayImage>() {
            override fun areItemsTheSame(@NonNull oldItem: PixabayImage, @NonNull newItem: PixabayImage): Boolean {
                return oldItem.userId === newItem.userId
            }

            override fun areContentsTheSame(@NonNull oldItem: PixabayImage, @NonNull newItem: PixabayImage): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun equals(obj: Any?): Boolean {
        if (obj === this)
            return true
        val pixabayImage = obj as PixabayImage?
        return pixabayImage!!.id === this.id
    }
}


