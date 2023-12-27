package com.example.sosmedinstagram.ui.login

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.helper.getUser
import com.example.sosmedinstagram.helper.saveUser
import com.example.sosmedinstagram.helper.showSnackbar
import com.example.sosmedinstagram.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: MutableLiveData<Boolean> = _isLogin

    fun checkLogin(context: Context){
        CoroutineScope(Dispatchers.IO).run {
            val pref = getUser(context)
            if (pref.email !== null || pref.password !== null) {
                if (pref.email.equals("user@gmail.com") && pref.password.equals("user123")) {
                    _isLogin.postValue(true)
                    val user: UserModel = UserModel()
                    user.email = pref.email
                    user.password = pref.password
                    user.nama = pref.nama
                    user.nohp = pref.nohp
                    user.isLogin = true
                    val saveUser = saveUser(user,context)
                    showSnackbar(context, "Login Berhasil")
                    return@run
                }else{
                    _isLogin.postValue(false)
                    showSnackbar(context, "Username atau Password salah")
                    Log.d("TAG", "checkLogin: " + pref.email + " " + pref.password)
                    return@run
                }
            }else{
                _isLogin.postValue(false)
                showSnackbar(context, "Username atau Password belum diisi")
                return@run
            }
        }
    }
}