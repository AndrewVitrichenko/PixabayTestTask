package com.pixabay.testtask.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.testtask.R
import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.ui.base.BaseFragment
import com.pixabay.testtask.ui.details.DetailsFragment
import com.pixabay.testtask.ui.feed.list.FeedListAdapter
import com.pixabay.testtask.data.entity.getData
import com.pixabay.testtask.data.entity.getMessage
import com.pixabay.testtask.data.entity.getThrowable
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : BaseFragment(), FeedListAdapter.PixabayImagesListClickHandler {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var feedViewModel: FeedViewModel

    private lateinit var feedListAdapter: FeedListAdapter

    private var canPerformPerPageLoading : Boolean = true

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        feedViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        listenViewModelEvents()
        initSearchView()
        if (arguments != null) {
            val initialSearchText = arguments!!.getString(INITIAL_SEARCH_TEXT)
            searchView.setQuery(initialSearchText,false)
            feedViewModel.searchImagesByText(initialSearchText)
            arguments = null
        }
        searchView.onActionViewExpanded()

    }

    private fun initSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean  = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                val text = searchView.query.toString()
                if (text.isNotEmpty()){
                    feedListAdapter.setData(ArrayList())
                }
                hideKeyboard()
                feedViewModel.searchImagesByText(text)
                return false
            }
        })
    }

    override fun onPixabayImageClicked(pixabayImage: PixabayImage) {
        val detailsFragment = DetailsFragment.newInstance(pixabayImage)
        showFragment(detailsFragment,true)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            feedListAdapter = FeedListAdapter()
            feedListAdapter.setPixabayImagesListClickHandler(this@FeedFragment)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = feedListAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0)
                    {
                        val visibleItemCount = linearLayoutManager.childCount
                        val totalItemCount = linearLayoutManager.itemCount
                        val pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()

                        if (canPerformPerPageLoading) {
                            if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                                canPerformPerPageLoading = false
                                feedViewModel.loadNextPage()
                            }
                        }
                    }
                }
            })
        }
    }

    private fun listenViewModelEvents() {
        feedViewModel.imagesLiveData.observe(viewLifecycleOwner, Observer {
            updateListWithImages(it.getData())
            handleProgressBar(false)
            canPerformPerPageLoading = true
        })
        feedViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            handleError(it.getThrowable())
            handleProgressBar(false)
            canPerformPerPageLoading = true
        })
        feedViewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            handleProgressBar(true)
        })
        feedViewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            super.showMessage(it.getMessage())
            handleProgressBar(false)
            canPerformPerPageLoading = true
        })
    }

    private fun handleProgressBar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun updateListWithImages(imagesList: List<PixabayImage>) {
        feedListAdapter.setData(imagesList)
    }

    private fun handleError(throwable: Throwable) {
        super.showMessage(throwable.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        feedListAdapter.setPixabayImagesListClickHandler(null)
    }

}