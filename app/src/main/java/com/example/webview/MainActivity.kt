package com.example.webview

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webview.Adapter.ShopListAdapter
import com.example.webview.Adapter.clickitemListener

import com.example.webview.Database.Shop
import com.example.webview.Model.JsonPlaceHolderApi
import com.example.webview.ViewModel.ShopViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class MainActivity : AppCompatActivity(), clickitemListener {

    lateinit var shopViewModel: ShopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        add.setOnClickListener {
            startActivityForResult(Intent(this, ScanActivity::class.java),1)
        }

        history.setOnClickListener {
            startActivity(Intent(this,HistoryActivity::class.java))
        }



        shopViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)


        val listUser = shopViewModel.allShops

        val adapter = ShopListAdapter(listUser,this)

        val adapter1 = listUser.let { ShopListAdapter(it,this) }



        shopViewModel.allShops.observe(this, Observer { words ->
            words?.let { adapter.setShops(it)}
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {


                val shopId = data?.getStringExtra("shopId")

                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api-customer.thaichana.com/shop/0001/")
                    .build()

                val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

                val mycall: Call<Shop> = jsonPlaceHolderApi.getMerchant(shopId!!)

                mycall.enqueue(object : Callback<Shop> {
                    override fun onFailure(call: Call<Shop>, t: Throwable) {
                        Log.e("ERROR",t.message.toString())
                    }

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onResponse(call: Call<Shop>, response: Response<Shop>) {


                        Log.i("tag","b4")

                        val shop :Shop = response.body()!!

//                        realPlace = shop.shopName
                        current_place.text = shop.shopName


                        Log.i("tag",shop.shopName)

                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss ")
                        val formatted = current.format(formatter)





                        val shopsave = Shop(shop.shopId,shop.appId,shop.shopName,shop.subcategory,shop.businessType,formatted)
                        shopViewModel.insert(shopsave)

                        Log.i("tag","save")



                    }
                })

            }
        }
    }

    override fun onItemClick(iten: Shop, position: Int) {
        Log.i("tag","clickedddddd")
        Log.i("tag",position.toString())
    }
}