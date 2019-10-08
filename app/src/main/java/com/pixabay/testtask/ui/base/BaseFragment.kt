package com.pixabay.testtask.ui.base

import android.content.Context
import android.widget.Toast
import com.pixabay.testtask.interfaces.IFragmentNavigationHandler
import com.pixabay.testtask.interfaces.IMessageHandler
import com.pixabay.testtask.util.Result
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    private var navigationHandler: IFragmentNavigationHandler? = null
    private var messageHandler: IMessageHandler? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initNavigationHandler()
    }

    private fun initNavigationHandler() {
        if (activity is IFragmentNavigationHandler) {
            navigationHandler = activity as IFragmentNavigationHandler
        } else {
            throw Exception("IFragmentNavigationHandler not implemented")
        }
    }

    private fun initMessageHandler(){
        if (activity is IMessageHandler) {
            messageHandler = activity as IMessageHandler
        } else {
            throw Exception("IMessageHandler not implemented")
        }
    }

    fun showFragment(fragmentToShow: BaseFragment, addtoBackStack: Boolean) {
        navigationHandler?.showFragment(fragmentToShow, addtoBackStack)
    }

    fun showMessage(message: String){
        messageHandler?.showMessage(message)
    }

    abstract fun getFragmentTag(): String

}