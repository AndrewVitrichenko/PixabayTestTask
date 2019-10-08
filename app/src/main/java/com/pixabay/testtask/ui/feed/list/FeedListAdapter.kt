package com.pixabay.testtask.ui.feed.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pixabay.testtask.R
import com.pixabay.testtask.data.entity.PixabayImage
import kotlinx.android.synthetic.main.list_feed_item.view.*


class FeedListAdapter : ListAdapter<PixabayImage,
        FeedListAdapter.PixabayImageItemViewHolder>(PixabayImage.DIFF_CALLBACK) {

    private var pixabayImagesListClickHandler : PixabayImagesListClickHandler? = null

    fun setPixabayImagesListClickHandler(pixabayImagesListClickHandler : PixabayImagesListClickHandler?){
        this.pixabayImagesListClickHandler = pixabayImagesListClickHandler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayImageItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_feed_item, parent, false)
        val viewHolder = PixabayImageItemViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val clickedImage = getItem(viewHolder.adapterPosition)
            pixabayImagesListClickHandler?.onPixabayImageClicked(clickedImage)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PixabayImageItemViewHolder, position: Int) {
        val pixabayImageItem = getItem(position)
        holder.bindTo(pixabayImageItem)
    }

    inner class PixabayImageItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(pixabayImageItem: PixabayImage) {
            itemView.apply {
                Glide.with(context).load(pixabayImageItem.largeImageURL).into(itemImageView)
                userNameTextView.text = pixabayImageItem.user
                tagsTextView.text = pixabayImageItem.tags
            }
        }
    }

    public interface PixabayImagesListClickHandler{
        fun onPixabayImageClicked(pixabayImage: PixabayImage)
    }
}