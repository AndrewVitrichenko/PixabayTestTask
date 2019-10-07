package com.pixabay.testtask

import android.os.Bundle
import com.pixabay.testtask.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getFragmentContainer(): Int  = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}
