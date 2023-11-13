package com.beomboo.mvvm.utils

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.crashlytics.setCustomKeys

class FirebaseCrashlytics {
    fun setCustomKey(){
        val crashtics = Firebase.crashlytics
        crashtics.setCustomKeys {
            key("name","BeomBoo")
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