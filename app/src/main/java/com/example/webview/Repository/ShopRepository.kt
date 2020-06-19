package com.example.webview.Repository

import androidx.lifecycle.LiveData
import com.example.webview.Database.Shop
import com.example.webview.Database.ShopDao


class ShopRepository(private val shopDao: ShopDao) {

    val allShops: LiveData<List<Shop>> = shopDao.getShop()


    suspend fun insert(shop: Shop){
        shopDao.insert(shop)
    }





}