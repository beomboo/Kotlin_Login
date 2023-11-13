package com.beomboo.mvvm.viewModel

import android.app.Application
import android.app.job.JobInfo
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.beomboo.mvvm.model.UserEntity
import com.beomboo.mvvm.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// ViewModel(1) : View(N) Relation

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    val TAG: String = "[Be][ViewModel]"

    // Repository
    val repository: Repository = Repository(context)

    // 로그인 성공 여부
    private var _logInYn = MutableLiveData<UserEntity>()

    // 회원가입 결과값(pid)
    private var _registrationResult = MutableLiveData<List<Long>>()


    val logInYn: LiveData<UserEntity> get() = _logInYn
    val registrationResult: LiveData<List<Long>> get() = _registrationResult

    fun login(id: String, pass: String) = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) {repository.select(id, pass)}
        if(result != null){
            _logInYn.value = result!!
        }else{
            toastMessage()
        }
        Log.d(TAG, "id($id) pass($pass)")
    }


    // [회원가입]
    // SUCCESS : pid,
    // FAIL : ERROR
    fun setUserInfo(userEntity: UserEntity)= viewModelScope.launch {
        try {
            val pid = withContext(Dispatchers.IO) { repository.insert(userEntity) }
            _registrationResult.value = pid
            Log.d(TAG, "result : ${pid}")
        } catch (e: Exception) {
            Log.e(TAG, "setMessage error >> $e")
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
