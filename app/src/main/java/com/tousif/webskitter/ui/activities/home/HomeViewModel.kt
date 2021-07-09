package com.tousif.webskitter.ui.activities.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tousif.webskitter.data.models.AllCategory
import com.tousif.webskitter.data.models.Item
import com.tousif.webskitter.data.models.ItemViewData
import com.tousif.webskitter.ui.fragments.photos.PhotosAdapter


class HomeViewModel : ViewModel() {
    var photoAdapter: PhotosAdapter = PhotosAdapter()
    val searchList: MutableList<ItemViewData> = mutableListOf()
    val photoList: MutableList<ItemViewData> = mutableListOf()
    val totalSelectedLD = MutableLiveData<Int>()

    init {
        totalSelectedLD.value = 0
    }


}