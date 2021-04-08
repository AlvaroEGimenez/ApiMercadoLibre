package com.meli.challenge.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
class Paging(@SerializedName("total")
             val total : Int = 0,
             @SerializedName("offset")
             val offset : Int = 0,
             @SerializedName("limit")
             val limit : Int = 0,
             @SerializedName("primary_results")
             val primary_results : Int = 0):Parcelable