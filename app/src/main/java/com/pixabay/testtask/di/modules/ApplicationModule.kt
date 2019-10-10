package com.pixabay.testtask.di.modules

import android.content.Context
import com.pixabay.testtask.PixabayApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindContext(application: PixabayApplication): Context
}