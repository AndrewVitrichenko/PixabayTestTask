package com.pixabay.testtask.interfaces

import com.pixabay.testtask.ui.base.BaseFragment

interface IFragmentNavigationHandler {

    fun showFragment(fragmentToShow: BaseFragment, addToBackStack : Boolean)
}