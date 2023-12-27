package com.example.sosmedinstagram.ui.fragment.post

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sosmedinstagram.helper.showSnackbar
import com.example.sosmedinstagram.model.PostModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class PostViewModel(application: Application): AndroidViewModel(application) {
    private val _sendPost = MutableLiveData<PostModel>()
    val sendPost = MutableLiveData<PostModel>()

    fun uploadPost(context: Context, post: PostModel) {
        CoroutineScope(Dispatchers.IO).run {
            _sendPost.postValue(post)
            showSnackbar(context,"Post Berhasil Ditambahkan")
        }
    }

}