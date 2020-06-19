package com.example.webview.Adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.webview.Database.Shop
import com.example.webview.R
import com.example.webview.ViewModel.ShopViewModel
//import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ShopListAdapter(val shop:LiveData<List<Shop>>, var clickListener: clickitemListener) : RecyclerView.Adapter<ShopListAdapter.ShopViewHolder>() {


    private var shops = emptyList<Shop>()

    lateinit var shopViewModel: ShopViewModel



    inner class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun initialize(item: Shop,action: clickitemListener) {
            itemView.setOnClickListener {
                action.onItemClick(item,adapterPosition)

            }

        }

        val wordItemView: TextView = itemView.findViewById(R.id.shopName)
        val dateTimeView: TextView = itemView.findViewById(R.id.datetime)

        //ตัวที่จะแสดงใน recyclerview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)

        return ShopViewHolder(itemView)
        //recyclerview_item ก็เตือตัวบน แต่อันนี้สร้างมั้ง เหมือนจับตัวบนยัดใส่รีไซเคิล หรอ
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val current = shops[position]
        //words ตอนแรกประกาศเป็น empty
        holder.wordItemView.text = current.shopName
        holder.dateTimeView.text = current.datetime

        val currentWord = holder.adapterPosition

        holder.initialize(shops.get(position), clickListener)
    }



    internal fun setShops(shops: List<Shop>){
        this.shops = shops
        notifyDataSetChanged()
    }

    override fun getItemCount() = shops.size




}

interface clickitemListener {

    fun onItemClick(iten:Shop,position: Int)

}
