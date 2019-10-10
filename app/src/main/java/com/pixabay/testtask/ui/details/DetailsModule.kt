package com.pixabay.testtask.ui.details

import androidx.lifecycle.ViewModel
import com.pixabay.testtask.di.ViewModelKey
import com.pixabay.testtask.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesDetailsFragment(): DetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsFragmentViewModel(viewModel: DetailsViewModel): ViewModel
}