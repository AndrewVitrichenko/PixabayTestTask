package com.pixabay.testtask.di.modules

import android.content.Context
import com.pixabay.testtask.util.NetworkUtil
import com.pixabay.testtask.util.ResourceUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun providesNetworkUtil(context: Context) = NetworkUtil(context)

    @Singleton
    @Provides
    fun providesResourceUtil(context: Context) = ResourceUtil(context)
}