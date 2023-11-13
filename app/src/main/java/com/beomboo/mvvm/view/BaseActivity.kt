package com.beomboo.mvvm.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {
    private var _binding: T? = null
    val binding get() = _binding!!
    val TAG: String = "[Be][${javaClass.simpleName}]"
    private val context = this
    private var backPressCount = 0
    private val callbackBackPress = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Toast.makeText(context, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
            backPressCount++
            if (backPressCount > 1) finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResId)

        this.onBackPressedDispatcher.addCallback(this, callbackBackPress)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}