package com.example.architecturepatterns.cleanmvvm.common

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(any: Any) {
    Glide.with(this.context)
        .load(any)
        .centerCrop()
        .into(this)
}