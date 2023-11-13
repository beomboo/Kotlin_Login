package com.beomboo.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beomboo.mvvm.R
import com.beomboo.mvvm.databinding.ActivityLoginBinding
import com.beomboo.mvvm.model.UserEntity
import com.beomboo.mvvm.viewModel.MainViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login){
    private val viewModel by lazy {  ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllUserData()

        viewModel.logInYn.observe(this, Observer<UserEntity> { curData->
            if(!curData.equals("")){
                Log.d(TAG,"로그인 성공 >>  $curData")
                moveToDashBoardView()
            }
        })

        binding.loginBtn.setOnClickListener{
            val id = binding.idInputField.text.toString()
            val pass = binding.passInputField.text.toString()
            viewModel.login(id,pass)
        }

        binding.joinUser.setOnClickListener{
            moveMainActivity()
        }
    }

    fun moveMainActivity(){
       val intent = Intent(this, RegisterActivity::class.java)
       startActivity(intent)
    }

    fun moveToDashBoardView(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}