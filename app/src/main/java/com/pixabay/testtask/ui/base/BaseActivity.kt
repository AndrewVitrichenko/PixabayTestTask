package com.pixabay.testtask.ui.base

import android.widget.Toast
import com.pixabay.testtask.interfaces.IFragmentNavigationHandler
import com.pixabay.testtask.interfaces.IMessageHandler
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(),IFragmentNavigationHandler, IMessageHandler {

    override fun showFragment(fragmentToShow: BaseFragment, addToBackStack: Boolean) {
        supportFragmentManager.let {
            if (!addToBackStack) {
                it.beginTransaction()
                    .replace(getFragmentContainer(), fragmentToShow, fragmentToShow.getFragmentTag())
                    .commitAllowingStateLoss()
            } else {
                val fragment = it.findFragmentByTag(fragmentToShow.getFragmentTag())
                if (fragment == null) {
                    it.beginTransaction()
                        .replace(getFragmentContainer(), fragmentToShow, fragmentToShow.getFragmentTag())
                        .addToBackStack(fragmentToShow.getFragmentTag())
                        .commitAllowingStateLoss()
                } else {
                    val castedFragment = fragment as BaseFragment
                    it.popBackStack(castedFragment.getFragmentTag(), 0)
                }
            }
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