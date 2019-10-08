package com.pixabay.testtask.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pixabay.testtask.R
import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.ui.base.BaseFragment
import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var detailsViewModel: DetailsViewModel

    companion object{
        val TAG : String = DetailsFragment::class.java.simpleName

        fun newInstance(pixabayImage: PixabayImage): DetailsFragment {
            return DetailsFragment()
        }
    }

    override fun getFragmentTag(): String  = TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailsViewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_details,container,false)
    }


}