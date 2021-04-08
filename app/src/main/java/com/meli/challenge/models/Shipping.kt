package com.meli.challenge.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class Shipping(@SerializedName("free_shipping")
                    val freeShipping: Boolean = false):Parcelable
