package com.pixabay.testtask.di.modules

import com.pixabay.testtask.di.scopes.ActivityScoped
import com.pixabay.testtask.ui.PixabayActivity
import com.pixabay.testtask.ui.details.DetailsModule
import com.pixabay.testtask.ui.feed.FeedModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FeedModule::class,DetailsModule::class])
    abstract fun pixabayActivity(): PixabayActivity


}