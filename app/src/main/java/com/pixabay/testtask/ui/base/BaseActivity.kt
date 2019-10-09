package com.pixabay.testtask.ui.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.pixabay.testtask.R
import com.pixabay.testtask.interfaces.IFragmentNavigationHandler
import com.pixabay.testtask.interfaces.IKeyboardHandler
import com.pixabay.testtask.interfaces.IMessageHandler
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), IFragmentNavigationHandler,
    IMessageHandler,IKeyboardHandler {

    override fun showFragment(fragmentToShow: BaseFragment, addToBackStack: Boolean) {
        supportFragmentManager.let {
            val fragment = it.findFragmentByTag(fragmentToShow.getFragmentTag())
            if (fragment == null) {
                if (!addToBackStack) {
                    it.beginTransaction()
                        .setCustomAnimations(R.anim.slide_up, R.anim.slide_down)
                        .replace(getFragmentContainer(), fragmentToShow, fragmentToShow.getFragmentTag())
                        .commit()
                } else {
                    it.beginTransaction()
                        .setCustomAnimations(R.anim.slide_up, R.anim.slide_down)
                        .replace(getFragmentContainer(), fragmentToShow, fragmentToShow.getFragmentTag())
                        .addToBackStack(fragmentToShow.getFragmentTag())
                        .commit()
                }
            } else {
                val castedFragment = fragment as BaseFragment
                it.popBackStack(castedFragment.getFragmentTag(), 0)
            }
        }
    }

    override fun hideKeyboard() {
        val currentFocusedView = currentFocus?.windowToken
        if (currentFocusedView != null){
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocusedView, 0)
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    abstract fun getFragmentContainer(): Int


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}