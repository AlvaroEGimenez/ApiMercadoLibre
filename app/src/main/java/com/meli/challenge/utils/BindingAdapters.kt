package com.meli.challenge.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide.with
import com.meli.challenge.R
import java.text.NumberFormat
import java.util.*

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        with(view.context)
                .load(imageUrl)
                .error(R.drawable.ic_baseline_image_24)
                .into(view)
    }else{
        with(view.context)
            .load(R.drawable.ic_baseline_image_24)
            .into(view)
    }
}

@BindingAdapter("formatPrice")
fun formartPrice(textView: TextView, price: Float){
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    textView.text = currencyFormat.format(price)
    }