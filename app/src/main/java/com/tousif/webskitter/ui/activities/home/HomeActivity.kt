package com.tousif.webskitter.ui.activities.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tousif.webskitter.R
import com.tousif.webskitter.databinding.ActivityHomeBinding
import com.tousif.webskitter.utilities.extensons.getViewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var vm: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        vm = getViewModel { HomeViewModel() }
        binding.vm = vm
        binding.lifecycleOwner = this
    }

}
