package com.pixabay.testtask.ui.feed.list

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.pixabay.testtask.data.entity.PixabayImage

class PixabayImagesDiffCallback(private val oldList: List<PixabayImage>, private val newList: List<PixabayImage>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}