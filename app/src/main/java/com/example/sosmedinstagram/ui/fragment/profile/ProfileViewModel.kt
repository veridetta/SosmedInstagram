package com.example.sosmedinstagram.ui.fragment.profile

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

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    private val _getUser = MutableLiveData<UserModel>()
    val getUser: MutableLiveData<UserModel> = _getUser
    private val _saveUser = MutableLiveData<UserModel>()
    val saveUser: MutableLiveData<UserModel> = _saveUser

    fun loadUser(context: Context){
        CoroutineScope(Dispatchers.IO).run {
            val user = getUser(context)
            Log.d("ProfileViewModel", "loadUser: $user")
            _getUser.postValue(user)
        }
    }

    fun postUser(context: Context, user: UserModel){
        CoroutineScope(Dispatchers.IO).run {
            val save = saveUser(user, context)
            _saveUser.postValue(user)
            showSnackbar(context, "Berhasil Update Profile")
        }
    }
}