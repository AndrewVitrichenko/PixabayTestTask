package com.pixabay.testtask.di.components

import com.pixabay.testtask.PixabayApplication
import com.pixabay.testtask.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ApplicationModule::class, ActivityBindingModule::class,DataModule::class,
    ViewModelFactoryModule::class, NetworkModule::class])
interface PixabayApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: PixabayApplication): Builder

        fun build(): PixabayApplicationComponent
    }
}