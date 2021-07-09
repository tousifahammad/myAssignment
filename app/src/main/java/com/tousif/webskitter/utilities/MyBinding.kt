package com.tousif.webskitter.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.tousif.webskitter.app.MyApplication
import com.tousif.webskitter.utilities.extensons.trycatch

@BindingAdapter("image_url")
fun loadImageUrl(imageView: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return
    trycatch {
        Picasso.get().load(imageUrl).into(imageView)
    }
}

@BindingAdapter("load_image")
fun loadImage(imageView: ImageView, name: String) {
    trycatch {
        Picasso.get().load(MyApplication.images[(0..4).random()]).into(imageView)
    }
}



