package com.tousif.assignment.app

import android.app.Application
import com.tousif.assignment.R

class MyApplication : Application() {

    companion object {
        val images = mutableListOf<Int>(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5)
    }


    override fun onCreate() {
        super.onCreate()

    }


}