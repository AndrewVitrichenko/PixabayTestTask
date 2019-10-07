package com.pixabay.testtask

import com.pixabay.testtask.di.components.DaggerPixabayApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class PixabayApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerPixabayApplicationComponent
            .builder()
            .application(this)
            .build()
    }
}