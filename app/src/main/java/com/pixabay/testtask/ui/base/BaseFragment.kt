package com.pixabay.testtask.ui.base

import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.pixabay.testtask.interfaces.IMessageHandler
import dagger.android.support.DaggerFragment
import io.reactivex.Observable

abstract class BaseFragment : DaggerFragment(),IMessageHandler {

    abstract fun getFragmentTag(): String

    override fun showMessage(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun textChanges(editText: EditText): Observable<String> = RxTextView.textChanges(editText)
        .map { it.toString() }

    fun viewClick(view: View): Observable<Any> = RxView.clicks(view)
}