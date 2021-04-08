package com.meli.challenge.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName = "results")
data class Results(
    @SerializedName("results")
    val list: List<Product>
)