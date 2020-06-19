package com.example.learningroom.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.webview.Database.Shop
import com.example.webview.Database.ShopDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Shop::class), version = 1, exportSchema = false)
public abstract class ShopRoomDatabase : RoomDatabase() {

    abstract fun shopDao(): ShopDao


    private class ShopDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {



                }
            }
        }

    }

    companion object {

        private var INSTANCE: ShopRoomDatabase? = null


        fun getDatabase(
                context: Context
            ): ShopRoomDatabase {


//            (
//                    context: Context,
//            scope: CoroutineScope
//            ): WordRoomDatabase {

            synchronized(this) {

                var instance = INSTANCE

//            if (tempInstance != null){
//                return tempInstance
//            }

            if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShopRoomDatabase::class.java,
                        "shop_database"
                    )/*.addCallback(
                        WordDatabaseCallback(
                            scope
                        )
                    )
                        .build()*/
                        .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance

                }
                return instance
            }
        }
    }



}


