package com.beomboo.mvvm.view

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.beomboo.mvvm.R
import com.beomboo.mvvm.databinding.ActivityMainBinding
import com.beomboo.mvvm.utils.Constants

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        true.also { binding.webView.settings.javaScriptEnabled = it }
        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()

        binding.webView.loadUrl(Constants.BASE_URL)
    }
    override fun onBackPressed() {
        if(binding.webView.canGoBack()){
            // 웹싸이트에서 뒤로 갈 페이지가 존재 할 경우
            binding.webView.goBack()
        }
        else {
            super.onBackPressed()
        }
    }
}