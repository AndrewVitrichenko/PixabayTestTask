package com.pixabay.testtask.ui

import android.os.Bundle
import com.pixabay.testtask.R
import com.pixabay.testtask.ui.base.BaseActivity
import com.pixabay.testtask.ui.feed.FeedFragment

class PixabayActivity : BaseActivity() {

    override fun getFragmentContainer(): Int  = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(FeedFragment.newInstance(),true)
    }


}
