package com.beomboo.mvvm.utils

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.crashlytics.setCustomKeys

class FirebaseCore(value: List<Long>?) {
    init {
        setCustomKey(value)
    }

    fun setCustomKey(pid: List<Long>?) {
        Firebase.crashlytics.setCustomKeys {
            key("pid","$pid")
            key("my_bool_key", true) // boolean value
            key("my_double_key", 1.0) // double value
            key("my_float_key", 1.0f) // float value
            key("my_int_key", 1) // int value
        }
    }

    fun setFirebaseLog(){
        Firebase.crashlytics.log("sendLog 버튼 이벤트")
    }
}