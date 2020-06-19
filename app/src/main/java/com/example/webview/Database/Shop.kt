package com.example.webview.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "shop_table")

data class Shop(

    @PrimaryKey @ColumnInfo(name = "shopId")
    val shopId: String,

    @ColumnInfo(name = "appId")
    val appId: String,

    @ColumnInfo(name = "shopName")
    val shopName: String,

    @ColumnInfo(name = "subcategory")
    val subcategory: String,

    @ColumnInfo(name = "businessType")
    val businessType: String,

    @ColumnInfo(name = "datetime")
    val datetime: String


)

