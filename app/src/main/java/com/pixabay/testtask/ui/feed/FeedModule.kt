package com.pixabay.testtask.ui.feed

import androidx.lifecycle.ViewModel
import com.pixabay.testtask.di.ViewModelKey
import com.pixabay.testtask.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FeedModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesFeedFragment(): FeedFragment

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun bindFeedFragmentViewModel(viewModel: FeedViewModel): ViewModel
}