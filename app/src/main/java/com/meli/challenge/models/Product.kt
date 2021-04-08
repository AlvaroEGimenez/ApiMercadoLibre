package com.meli.challenge.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.NumberFormat
import java.util.*

@Entity(tableName = "product")
@Parcelize
data class Product @JvmOverloads constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : String,
    val title : String,
    val price : Float,
    val condition : String,
    val thumbnail : String,
    val acceptsMercadoPago: Boolean,
    @Ignore val paging: Paging? = null,
    @Ignore val address : ProductAddress? = null,
    @Ignore val shipping : Shipping? = null
):Parcelable
