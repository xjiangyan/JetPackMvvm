package com.huago.baselibrary.ext

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.Transition
import com.huago.baselibrary.R


//DataBinding自定义属性
@BindingAdapter(value = ["url", "placeholder", "error"], requireAll = false)
fun loadImage(image: ImageView?, url: String?, placeholder: Drawable?, error: Drawable?) {
    image?.let {
        Glide
            .with(image.context)
            .load(url)
            .placeholder(placeholder ?: image.context.getDrawable(R.mipmap.icon_empty))
            .error(error ?: image.context.getDrawable(R.mipmap.icon_empty))
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                )
            )
            .into(image)
    }
}


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
            .into(imageView)
    }
}

@BindingAdapter("imageBgUrl")
fun loadBgImage(viewGroup: ViewGroup, url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(viewGroup.context)
            .asBitmap()
            .load(url)
            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    val drawable: Drawable = BitmapDrawable(resource)
                    viewGroup.background = drawable
                }
            })
    }
}

@BindingAdapter("imageWrapUrl")
fun loadWrapImage(imageView: ImageView, url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
            .into(imageView)
    }
}

@BindingAdapter("imageCircleUrl")
fun loadCircleImage(imageView: ImageView, url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageView)
    }
}

