package com.meli.challenge.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class ProductAddress(@SerializedName("state_name")
                     val stateName: String = "",
                     @SerializedName("city_name")
                     val cityName: String = ""): Parcelable
