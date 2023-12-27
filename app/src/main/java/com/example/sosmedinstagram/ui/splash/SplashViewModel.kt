package com.example.sosmedinstagram.ui.splash

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.helper.getUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SplashViewModel(application: Application): AndroidViewModel(application) {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: MutableLiveData<Boolean> = _isLogin

    fun checkLogin(context:Context){
        CoroutineScope(Dispatchers.IO).run {
            val pref = getUser(context)
            if (pref.email == null || pref.password == null){
                _isLogin.postValue(false)
                return@run
            }
            _isLogin.postValue(true)
        }
    }
}