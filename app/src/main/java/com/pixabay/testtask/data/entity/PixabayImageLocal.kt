package com.pixabay.testtask.data.entity

data class PixabayImageLocal (
    val id : String,
    val pageURL : String,
    val type : String,
    val tags : String,
    val previewURL : String,
    val previewWidth : Int,
    val previewHeight : Int,
    val webformatURL : String,
    val webformatWidth : Int,
    val webformatHeight : Int,
    val largeImageURL : String,
    val fullHDURL : String,
    val imageURL : String,
    val imageWidth : Int,
    val imageHeight : Int,
    val imageSize : Int,
    val views : Int,
    val downloads : Int,
    val favorites : Int,
    val likes : Int,
    val comments : Int,
    val userId : String,
    val user : String,
    val userImageURL : String
)