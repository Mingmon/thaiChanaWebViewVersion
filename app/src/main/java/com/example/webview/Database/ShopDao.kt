package com.example.webview.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopDao {

    @Query("SELECT * from shop_table ORDER BY datetime ASC")
    fun getShop(): LiveData<List<Shop>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(shop: Shop)

    @Query("DELETE FROM shop_table")
    suspend fun deleteAll()

    
    










}