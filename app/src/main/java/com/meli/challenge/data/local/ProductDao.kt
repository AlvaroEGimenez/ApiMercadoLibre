package com.meli.challenge.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.meli.challenge.models.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getLastSearch() : LiveData<List<Product>>

    @Query("DELETE FROM product")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Product)
}