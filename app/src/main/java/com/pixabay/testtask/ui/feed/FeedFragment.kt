package com.pixabay.testtask.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixabay.testtask.R
import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.ui.base.BaseFragment
import com.pixabay.testtask.ui.details.DetailsFragment
import com.pixabay.testtask.ui.feed.list.FeedListAdapter
import com.pixabay.testtask.util.getData
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : BaseFragment(), FeedListAdapter.PixabayImagesListClickHandler {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var feedViewModel: FeedViewModel

    private lateinit var feedListAdapter: FeedListAdapter

    companion object {
        val TAG: String = FeedFragment::class.java.simpleName
        val INITIAL_SEARCH_TEXT = "initial_search_text"

        fun newInstance(initialSearchText: String): FeedFragment {
            val args = Bundle()
            args.putString(INITIAL_SEARCH_TEXT, initialSearchText)
            val feedFragment = FeedFragment()
            feedFragment.arguments = args
            return feedFragment
        }
    }

    override fun getFragmentTag(): String = TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        feedViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        listenViewModelEvents()
        searchButton.setOnClickListener {
            feedViewModel.searchImagesByText(searchEditText.text.toString())
        }
        if (arguments != null) {
            val initialSearchText = arguments!!.getString(INITIAL_SEARCH_TEXT)
            searchEditText.setText(initialSearchText)
            searchButton.performClick()
            arguments = null
        }

    }

    override fun onPixabayImageClicked(pixabayImage: PixabayImage) {
        val detailsFragment = DetailsFragment.newInstance(pixabayImage)
        showFragment(detailsFragment,true)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            feedListAdapter = FeedListAdapter()
            feedListAdapter.setPixabayImagesListClickHandler(this@FeedFragment)
            layoutManager = LinearLayoutManager(context)
            adapter = feedListAdapter
        }
    }

    private fun listenViewModelEvents() {
        feedViewModel.imagesLiveData.observe(viewLifecycleOwner, Observer {
            updateListWithImages(it.getData())
        })
        feedViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            handleError(it.getData())
        })
        feedViewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            //todo : show progress
        })
        feedViewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            super.showMessage(it.getData())
        })
    }

    private fun updateListWithImages(imagesList: List<PixabayImage>) {
        feedListAdapter.submitList(imagesList)
    }

    private fun handleError(throwable: Throwable) {
        super.showMessage(throwable.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        feedListAdapter.setPixabayImagesListClickHandler(null)
    }

}