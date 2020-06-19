package com.example.webview.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.learningroom.Database.ShopRoomDatabase

import com.example.webview.Database.Shop
import com.example.webview.Repository.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ShopRepository

    val allShops: LiveData<List<Shop>>


    init {
        val shopsDao = ShopRoomDatabase.getDatabase(application).shopDao()

//        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()

        repository = ShopRepository(shopsDao)

        allShops = repository.allShops

    }

    fun insert(shop: Shop) = viewModelScope.launch(Dispatchers.IO ) {
        repository.insert(shop)
    }






}