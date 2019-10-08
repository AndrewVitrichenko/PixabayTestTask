package com.pixabay.testtask.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pixabay.testtask.R
import com.pixabay.testtask.ui.base.BaseFragment
import com.pixabay.testtask.util.data
import com.pixabay.testtask.util.succeeded
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var feedViewModel: FeedViewModel

    companion object{
        val TAG : String = FeedFragment::class.java.simpleName

        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    override fun getFragmentTag(): String  = TAG

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        feedViewModel = ViewModelProviders.of(this,viewModelFactory).get(FeedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_feed,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedViewModel.imagesLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("IMAGES",it.data().toString())
        })
        feedViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("IMAGES_ERROR",it.data().toString())
        })
        feedViewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("IMAGES_PROGRESS",it.data().toString())
        })
        feedViewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("IMAGES_MESSAGE",it.data().toString())
        })
        searchButton.setOnClickListener {
            feedViewModel.searchImagesByText(searchEditText.text.toString())
        }

    }

}