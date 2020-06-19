package com.example.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webview.Adapter.ShopListAdapter
import com.example.webview.Adapter.clickitemListener
import com.example.webview.ViewModel.ShopViewModel
import androidx.lifecycle.Observer
import com.example.webview.Database.Shop


class HistoryActivity : AppCompatActivity(), clickitemListener {
    
    lateinit var shopViewModel: ShopViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewHistory)

        shopViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)


        val listUser = shopViewModel.allShops

        val adapter = ShopListAdapter(listUser,this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        shopViewModel.allShops.observe(this, Observer { shops ->
            shops?.let {

                adapter.setShops(it)

            }
        })
    }

    override fun onItemClick(item: Shop, position: Int) {

        Log.i("tag","clickedddddd")

        val url = "https://qr.thaichana.com/?appId=0001&shopId=${item.shopId}"

//        Toast.makeText(this,url, Toast.LENGTH_SHORT).show()

        val intent = Intent(this,GoToUrl::class.java)
        intent.putExtra("url",url)
        startActivity(intent)


    }
}