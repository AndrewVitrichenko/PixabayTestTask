package com.pixabay.testtask.di.modules

import android.content.Context
import com.pixabay.testtask.PixabayApplication
import javax.inject.Singleton
import dagger.Binds
import dagger.Module


@Module
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindContext(application: PixabayApplication): Context
}