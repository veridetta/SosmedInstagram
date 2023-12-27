package com.example.sosmedinstagram.ui.post

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sosmedinstagram.helper.getPost
import com.example.sosmedinstagram.helper.getUser
import com.example.sosmedinstagram.model.CommentModel
import com.example.sosmedinstagram.model.PostModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class PostDetailVM(application: Application): AndroidViewModel(application) {
    private val _getPost = MutableLiveData<PostModel>()
    val getPost: MutableLiveData<PostModel> = _getPost
    private val _addComment = MutableLiveData<List<CommentModel>>()
    val addComment: MutableLiveData<List<CommentModel>> = _addComment
    private val _getComment = MutableLiveData<List<CommentModel>>()
    val getComment: MutableLiveData<List<CommentModel>> = _getComment
    val list = mutableListOf<CommentModel>()

    private val _postComment = MutableLiveData<List<CommentModel>>()
    val postcomment: MutableLiveData<List<CommentModel>> = _postComment
    fun postComment(context: Context, comment: String){
        CoroutineScope(Dispatchers.IO).run {
            val user = getUser(context)
            val nama = user.nama ?: "User"
            val post = CommentModel()
            post.nama = user.nama
            post.userId = user.id
            post.date = getCurrentDate()
            post.time = getCurrentTime()
            post.comment = comment
            list.add(post)
            _postComment.postValue(list)
        }
    }
    fun setPost(context: Context) {
        CoroutineScope(Dispatchers.IO).run {
            val getpost = getPost(context)
            _getPost.postValue(getpost)
        }
    }
    fun addcomment(context: Context){
        CoroutineScope(Dispatchers.IO).run {
            for (i in 1..10) {
                val post = CommentModel()
                post.nama = "User${Random().nextInt(100)}"
                post.userId = "UserID${Random().nextInt(1000)}"
                post.date = getCurrentDate()
                post.time = getCurrentTime()
                post.comment = randomComment()
                list.add(post)
            }
            _addComment.postValue(list)
        }
    }
    fun loadComment(context: Context){
        CoroutineScope(Dispatchers.IO).run {
            _getComment.postValue(list)
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
    fun randomComment(): String {
        val comments = arrayOf(
            "Great post!",
            "Love it!",
            "Amazing!",
            "Nice picture!",
            "Awesome!",
            "Fantastic!",
            "Beautiful!",
            "Well done!",
            "Impressive!",
            "Keep it up!"
        )

        val random = java.util.Random()
        val numberOfComments = random.nextInt(5) + 1 // Generate between 1 to 5 comments

        val randomComments = mutableListOf<String>()

        for (i in 0 until numberOfComments) {
            val randomIndex = random.nextInt(comments.size)
            randomComments.add(comments[randomIndex])
        }

        //ambil 1 comment
        return randomComments[0]
    }
}