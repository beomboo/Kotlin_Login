package com.beomboo.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beomboo.mvvm.databinding.ActivityLoginBinding
import com.beomboo.mvvm.model.UserDao
import com.beomboo.mvvm.model.UserEntity
import com.beomboo.mvvm.utils.FirebaseCrashlytics
import com.beomboo.mvvm.viewModel.MainViewModel

class LoginActivity : AppCompatActivity(){
    val TAG : String = "[Be][View]"
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewModel 의 데이터를 관찰
        viewModel.signInYn.observe(this, Observer<Boolean> { curData->
            if(curData){
                Log.d(TAG,"회원가입 성공 >>  $curData")
                moveToMainView()
            }
        })

        // View 에서 Event 발생 시 ViewModel에 데이터 요청
        binding.sendBtn.setOnClickListener{
            var userEntity = UserEntity(
                uId = binding.idTextInputField.text.toString(),
                email = binding.emailTextInputField.text.toString(),
                name = binding.nameTextInputField.text.toString(),
                pass = binding.passTextInputField.text.toString()
            )
            viewModel.setUserInfo(userEntity)
        }
        setFirebaseCrashlytics()
    }


    private fun setFirebaseCrashlytics(){
        FirebaseCrashlytics().setCustomKey()

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    fun moveToMainView(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}