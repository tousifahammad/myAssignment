package com.tousif.assignment.ui.fragments.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.tousif.assignment.R
import com.tousif.assignment.data.constants.Types
import com.tousif.assignment.data.models.ItemViewData
import com.tousif.assignment.data.models.ResponseData
import com.tousif.assignment.databinding.FragmentPhotosBinding
import com.tousif.assignment.networking.ApiService
import com.tousif.assignment.networking.RetrofitInstance
import com.tousif.assignment.ui.activities.home.HomeViewModel
import com.tousif.assignment.utilities.extensons.*
import retrofit2.Response


class PhotosFragment : Fragment() {

    private lateinit var vm: HomeViewModel
    private lateinit var binding: FragmentPhotosBinding
    private lateinit var apiService: ApiService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = requireActivity().getViewModel { HomeViewModel() }
        binding.vm = vm

        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)

        initUI()

        //check for internet
        if (requireContext().isConnected)
            reqData()
        else {
            binding.progressBar.gone()
            requireContext().toast("Not connected through internet")
        }
    }

    private fun initUI() {
        binding.rvPhotos.adapter = vm.photoAdapter

        binding.etSearch.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                searchTitle(it.toString())
            } else {
                vm.photoAdapter.setData(vm.photoList)
            }
        }

        binding.ivClear.setOnClickListener {
            binding.etSearch.text.clear()
        }
    }


    private fun reqData() {
        binding.progressBar.visible()
        vm.photoList.clear()
        vm.photoAdapter.setData(vm.photoList)

        val responseLiveData: LiveData<Response<ResponseData>> = liveData {
            val response = apiService.getProducts()
            emit(response)
        }

        responseLiveData.observe(viewLifecycleOwner, {
            loadData(it.body())
        })
    }


    private fun loadData(photosRes: ResponseData?) {
        photosRes?.run {
            photosRes.data?.allCategory?.forEach {
                ItemViewData().apply {
                    type = Types.category
                    category = it
                    vm.photoList.add(this)
                }

                it.item.forEach { itemData ->
                    ItemViewData().apply {
                        type = Types.item
                        item = itemData
                        vm.photoList.add(this)
                    }
                }
            }

            vm.photoAdapter.setData(vm.photoList)
            binding.progressBar.gone()
        }

    }

    private fun searchTitle(searchText: String) {
        trycatch {
            vm.searchList.clear()
            val list = vm.photoList.filter { it.item != null }.filter { it.item!!.name.contains(searchText, true) }
            vm.searchList.addAll(list)
            vm.photoAdapter.setData(vm.searchList, true)
        }
    }

}