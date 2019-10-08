package com.pixabay.testtask.util

import android.content.Context
import androidx.annotation.StringRes

class ResourceUtil(private val context: Context) {

    fun getString(@StringRes stringResource : Int) : String{
        return context.getString(stringResource)
    }

}