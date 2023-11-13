package com.beomboo.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beomboo.mvvm.R
import com.beomboo.mvvm.databinding.ActivityRegisterBinding
import com.beomboo.mvvm.model.UserEntity
import com.beomboo.mvvm.utils.FirebaseCore
import com.beomboo.mvvm.viewModel.MainViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    private val viewModel by lazy {ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java]}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel 의 데이터를 관찰
        viewModel.registrationResult.observe(this, Observer<List<Long>?> { curData ->
            if (curData != null) {
                moveToMainView()
                resetRegisterForm()
                FirebaseCore(viewModel.registrationResult.value)
            }else{
                Toast.makeText(this,"회원가입이 실패하였습니다.",Toast.LENGTH_SHORT).show()
            }
        })

        // View 에서 Event 발생 시 ViewModel에 데이터 요청
        binding.setRegisterBtn.setOnClickListener{
            val userEntity = UserEntity(
                uId = binding.idTextInputField.text.toString(),
                email = binding.emailTextInputField.text.toString(),
                name = binding.nameTextInputField.text.toString(),
                pass = binding.passTextInputField.text.toString()
            )
            viewModel.setUserInfo(userEntity)
        }
    }

    fun moveToMainView() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun resetRegisterForm(){
        binding.nameTextInputField.text = null
        binding.idTextInputField.text = null
        binding.passTextInputField.text = null
        binding.emailTextInputField.text = null
    }
}