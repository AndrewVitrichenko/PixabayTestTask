package com.pixabay.testtask.ui.feed.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pixabay.testtask.R
import com.pixabay.testtask.data.entity.PixabayImage
import kotlinx.android.synthetic.main.list_feed_item.view.*


class FeedListAdapter : RecyclerView.Adapter<FeedListAdapter.PixabayImageItemViewHolder>() {
    override fun getItemCount(): Int = imagesList.size

    private var pixabayImagesListClickHandler: PixabayImagesListClickHandler? = null
    private var imagesList = ArrayList<PixabayImage>()

    fun setData(newImagesList: List<PixabayImage>) {
        val diffCallback = PixabayImagesDiffCallback(imagesList, newImagesList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        imagesList.clear()
        imagesList.addAll(newImagesList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setPixabayImagesListClickHandler(pixabayImagesListClickHandler: PixabayImagesListClickHandler?) {
        this.pixabayImagesListClickHandler = pixabayImagesListClickHandler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayImageItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_feed_item, parent, false)
        return PixabayImageItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PixabayImageItemViewHolder, position: Int) {
        val pixabayImageItem = imagesList[position]
        holder.bindTo(pixabayImageItem)
    }

    inner class PixabayImageItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(pixabayImageItem: PixabayImage) {
            itemView.apply {
                Glide.with(context).load(pixabayImageItem.getPreviewImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.image_placeholder))
                    .into(itemImageView)
                userNameTextView.text = String.format(
                    context.getString(
                        R.string.user_name_item,
                        pixabayImageItem.user
                    )
                )
                tagsTextView.text = String.format(
                    context.getString(
                        R.string.tags_item,
                        pixabayImageItem.tags
                    )
                )
                setOnClickListener {
                    val clickedImage = imagesList[adapterPosition]
                    pixabayImagesListClickHandler?.onPixabayImageClicked(clickedImage)
                }
            }
        }
    }

    public interface PixabayImagesListClickHandler {
        fun onPixabayImageClicked(pixabayImage: PixabayImage)
    }
}