package com.pixabay.testtask.ui.base

import android.widget.Toast
import com.pixabay.testtask.interfaces.IMessageHandler
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(),IMessageHandler {

    abstract fun getFragmentTag(): String

    override fun showMessage(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}