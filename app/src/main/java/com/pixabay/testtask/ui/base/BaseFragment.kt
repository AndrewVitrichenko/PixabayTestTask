package com.pixabay.testtask.ui.base

import android.content.Context
import com.pixabay.testtask.interfaces.IFragmentNavigationHandler
import com.pixabay.testtask.interfaces.IKeyboardHandler
import com.pixabay.testtask.interfaces.IMessageHandler
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    private var navigationHandler: IFragmentNavigationHandler? = null
    private var messageHandler: IMessageHandler? = null
    private var keyboardHandler: IKeyboardHandler? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initNavigationHandler()
        initMessageHandler()
        initKeyboardHandler()
    }

    private fun initNavigationHandler() {
        if (activity is IFragmentNavigationHandler) {
            navigationHandler = activity as IFragmentNavigationHandler
        } else {
            throw Exception("IFragmentNavigationHandler is not implemented")
        }
    }

    private fun initMessageHandler(){
        if (activity is IMessageHandler) {
            messageHandler = activity as IMessageHandler
        } else {
            throw Exception("IMessageHandler is not implemented")
        }
    }

    private fun initKeyboardHandler(){
        if (activity is IKeyboardHandler) {
            keyboardHandler = activity as IKeyboardHandler
        } else {
            throw Exception("IKeyboardHandler is not implemented")
        }
    }

    fun showFragment(fragmentToShow: BaseFragment, addtoBackStack: Boolean) {
        navigationHandler?.showFragment(fragmentToShow, addtoBackStack)
    }

    fun showMessage(message: String){
        messageHandler?.showMessage(message)
    }

    fun hideKeyboard(){
        keyboardHandler?.hideKeyboard()
    }

    abstract fun getFragmentTag(): String

}