package com.beomboo.mvvm.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import com.beomboo.mvvm.model.UserDao
import com.beomboo.mvvm.model.UserEntity
import com.beomboo.mvvm.repository.Repository
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    val TAG: String = "[Be][ViewModel]"

    // Repository
    val repository: Repository = Repository(context)

    // 회원가입 성공 여부
    private var _signInYn = MutableLiveData<Boolean>()
    val signInYn: LiveData<Boolean> get() = _signInYn

    // 로그인 성공 여부
    private var _logInYn = MutableLiveData<UserEntity>()
    val logInYn: LiveData<UserEntity> get() = _logInYn

    fun login(id: String, pass: String) = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) {
            repository.select(id, pass)
        }
        if(result != null){
            _logInYn.value = result!!
        }else{
            toastMessage()
        }
        Log.d(TAG, "id($id) pass($pass)")
    }

    fun setUserInfo(userEntity: UserEntity) = viewModelScope.launch {
        try {
            withContext(Dispatchers.IO) {
                _signInYn.value = repository.insert(userEntity)
            }
            Log.d(TAG, "result : ${_signInYn.value}")
        } catch (e: Exception) {
            _signInYn.value = false
            toastMessage()
            Log.e(TAG, "setMessage error $e")
        }
    }

    fun getAllUserData() = viewModelScope.launch {
        val list = withContext(Dispatchers.IO) { repository.getAll() }
        listOf(list).forEach {
            Log.d(TAG, "$it")
        }
    }

    fun toastMessage(){
        Toast.makeText(context,"정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }
}
