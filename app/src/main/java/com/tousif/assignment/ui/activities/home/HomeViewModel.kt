package com.tousif.assignment.ui.activities.home


import androidx.lifecycle.ViewModel
import com.tousif.assignment.data.models.ItemViewData
import com.tousif.assignment.ui.fragments.photos.PhotosAdapter


class HomeViewModel : ViewModel() {
    var photoAdapter: PhotosAdapter = PhotosAdapter()
    val searchList: MutableList<ItemViewData> = mutableListOf()
    val photoList: MutableList<ItemViewData> = mutableListOf()
}