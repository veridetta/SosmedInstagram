package com.example.sosmedinstagram.ui.register

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.helper.getUser
import com.example.sosmedinstagram.helper.showSnackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class RegisterViewModel(application: Application): AndroidViewModel(application) {
    private val _isRegister = MutableLiveData<Boolean>()
    val isRegister: MutableLiveData<Boolean> = _isRegister

    fun checkRegister(context: Context) {
        CoroutineScope(Dispatchers.IO).run {
            val pref = getUser(context)
            if (pref.email !== null || pref.password !== null) {
                _isRegister.postValue(true)
                showSnackbar(context, "Register Berhasil")
                return@run
            } else {
                _isRegister.postValue(false)
                showSnackbar(context, "Register Gagal")
                return@run
            }
        }
    }
}