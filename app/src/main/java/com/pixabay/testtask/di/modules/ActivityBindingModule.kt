package com.pixabay.testtask.di.modules

import dagger.Module
import com.pixabay.testtask.ui.PixabayActivity
import dagger.android.ContributesAndroidInjector
import com.pixabay.testtask.di.scopes.ActivityScoped
import com.pixabay.testtask.ui.details.DetailsModule
import com.pixabay.testtask.ui.feed.FeedModule

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FeedModule::class,DetailsModule::class])
    abstract fun pixabayActivity(): PixabayActivity


}