package com.tousif.assignment.ui.fragments.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tousif.assignment.R
import com.tousif.assignment.data.constants.Types
import com.tousif.assignment.data.models.ItemViewData
import com.tousif.assignment.databinding.ItemviewCategoryBinding
import com.tousif.assignment.databinding.ItemviewPhotoBinding

class PhotosAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<ItemViewData>()
    private var isSearching = false

    fun setData(newList: MutableList<ItemViewData>, searching: Boolean = false) {
        isSearching = searching

        val diffCallback = PhotosDiffCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Types.category -> CategoryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.itemview_category, parent, false))
            else -> ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.itemview_photo, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {

            Types.category -> {
                val categoryViewHolder = holder as CategoryViewHolder
                categoryViewHolder.bind(list[position])
            }
            else -> {
                val itemViewHolder = holder as ItemViewHolder
                itemViewHolder.bind(list[position])
            }
        }
    }

    inner class CategoryViewHolder(private val binding: ItemviewCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemViewData) {
            binding.category = item.category
            binding.executePendingBindings()
        }
    }

    inner class ItemViewHolder(private val binding: ItemviewPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemViewData) {
            binding.item = data.item
            binding.executePendingBindings()
        }
    }


}