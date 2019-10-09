package com.pixabay.testtask.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.pixabay.testtask.R
import com.pixabay.testtask.data.entity.PixabayImage
import com.pixabay.testtask.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var detailsViewModel: DetailsViewModel

    private var pixabayImage: PixabayImage? = null

    companion object {
        val PIXABAY_IMAGE = "pixabay_image"
        val TAG: String = DetailsFragment::class.java.simpleName

        fun newInstance(pixabayImage: PixabayImage): DetailsFragment {
            val args = Bundle()
            args.putParcelable(PIXABAY_IMAGE, pixabayImage)
            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = args
            return detailsFragment
        }
    }

    override fun getFragmentTag(): String = TAG

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPixabayImage()
    }

    private fun initPixabayImage() {
        pixabayImage = arguments?.getParcelable(PIXABAY_IMAGE)
        if (pixabayImage == null) {
            throw Exception("Pixabayimage entity is null")
        }
        Glide.with(context).load(pixabayImage?.getImage()).into(pixabayImageView)
        Glide.with(context).load(pixabayImage?.userImageURL).into(userImageView)
        userNameTextView.text = String.format(
            context?.getString(
                R.string.user_name_item,
                pixabayImage?.user
            ).toString()
        )
        tagsTextView.text = String.format(
            context?.getString(
                R.string.tags_item,
                pixabayImage?.tags
            ).toString()
        )
        likeCountTextView.text = pixabayImage?.likes.toString()
        favoriteCountTextView.text = pixabayImage?.favorites.toString()
        commentCountTextView.text = pixabayImage?.comments.toString()
    }


}