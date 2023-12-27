package com.example.sosmedinstagram.ui.fragment.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sosmedinstagram.model.PostModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class HomeFragmentVM(application: Application): AndroidViewModel(application) {
    private val _getPost = MutableLiveData<List<PostModel>>()
    val getPost: MutableLiveData<List<PostModel>> = _getPost
    private val _addList = MutableLiveData<List<PostModel>>()
    val addList: MutableLiveData<List<PostModel>> = _addList
    private val list = mutableListOf<PostModel>()
    fun loadPost(context :Context){
        CoroutineScope(Dispatchers.IO).run {
            _getPost.postValue(list)
        }
    }
    fun addPost(context: Context){
        CoroutineScope(Dispatchers.IO).run {
            for (i in 1..10) {
                val post = PostModel()
                post.nama = "User${Random().nextInt(100)}"
                post.userId = "UserID${Random().nextInt(1000)}"
                post.caption = "Random Caption ${Random().nextInt(100)}"
                post.image = "https://picsum.photos/200"
                post.date = getCurrentDate()
                post.time = getCurrentTime()
                post.like = "0"
                post.comment = "0"
                post.share = "0"
                post.id = "PostID${Random().nextInt(10000)}"
                list.add(post)
            }
            _addList.postValue(list)
        }
    }

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun getCurrentTime(): String {
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return timeFormat.format(Date())
    }
}