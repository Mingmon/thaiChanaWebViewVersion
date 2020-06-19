package com.example.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_go_to_url.*

class GoToUrl : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_url)

        var url: String = intent.getStringExtra("url")
        var shopId: String = intent.getStringExtra("shopId")

//        textView.text = url
//        textView2.text = shopId

        Log.i("tag","$url and $shopId")


        myWebView.webViewClient = object : WebViewClient(){

            override fun shouldOverrideUrlLoading(view: WebView?,url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

        }

        myWebView.loadUrl(url)
            myWebView.settings.javaScriptEnabled    =   true
            myWebView.settings.allowContentAccess   =   true
            myWebView.settings.domStorageEnabled    =   true
            myWebView.settings.useWideViewPort      =   true
            myWebView.settings.setAppCacheEnabled(true)
    }
}