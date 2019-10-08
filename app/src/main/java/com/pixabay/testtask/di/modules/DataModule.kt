package com.pixabay.testtask.di.modules

import com.pixabay.testtask.util.Scheduler
import com.pixabay.testtask.util.SchedulerProvider
import com.pixabay.testtask.data.datasource.PixabayDataSource
import com.pixabay.testtask.data.datasource.PixabayRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindPixabayDataSource(pixabayRepository: PixabayRepository): PixabayDataSource

    @Binds
    @Singleton
    abstract fun bindScheduler(scheduler: Scheduler) : SchedulerProvider

}