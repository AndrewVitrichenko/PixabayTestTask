package com.pixabay.testtask.ui.feed.dialog

import com.pixabay.testtask.data.entity.PixabayImage

interface IDetailsDialogFragmentCallback {

    fun onUserApproval(pixabayImage: PixabayImage?)

    fun onUserDenial(pixabayImage: PixabayImage?)
}