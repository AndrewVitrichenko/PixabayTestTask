package com.pixabay.testtask

import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.data.entity.list.PixabayImagesList

object TestData {

    fun getTestData() : PixabayImagesList{
        val list = ArrayList<PixabayImage>()
        list.add(PixabayImage())
        return PixabayImagesList(0,0,list)
    }
}