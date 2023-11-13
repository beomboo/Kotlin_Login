package com.beomboo.mvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beomboo.mvvm.R
import com.beomboo.mvvm.databinding.ActivityLoginBinding
import com.beomboo.mvvm.databinding.ActivityMainBinding
import com.beomboo.mvvm.model.UserDao
import com.beomboo.mvvm.model.UserEntity
import com.beomboo.mvvm.utils.FirebaseCrashlytics
import com.beomboo.mvvm.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    val TAG : String = "[Be][View]"
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAllUserData()

        // ViewModel 의 데이터를 관찰
        viewModel.logInYn.observe(this, Observer<UserEntity> { curData->
            if(!curData.equals("")){
                Log.d(TAG,"로그인 성공 >>  $curData")
                moveToDashBoardView()
            }
        })

        binding.joinUser.setOnClickListener{
            moveMainActivity()
        }

        binding.loginBtn.setOnClickListener{
            viewModel.login(binding.idInputField.text.toString(), binding.passInputField.text.toString())
        }
    }

    fun moveMainActivity(){
       val intent = Intent(this, LoginActivity::class.java)
       startActivity(intent)
    }

    fun moveToDashBoardView(){
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }
}