package com.tousif.webskitter.ui.fragments.photos

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.tousif.webskitter.data.models.ItemViewData

class PhotosDiffCallback(private val oldList: List<ItemViewData>, private val newList: List<ItemViewData>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].category?._id == newList[newItemPosition].category?._id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val oldData = oldList[oldPosition]
        val newData = newList[newPosition]

        return oldData.category?._id == newData.category?._id
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}