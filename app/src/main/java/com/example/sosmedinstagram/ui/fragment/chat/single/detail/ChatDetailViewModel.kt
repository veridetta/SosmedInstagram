package com.example.sosmedinstagram.ui.fragment.chat.single.detail

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.helper.getCurrentDate
import com.example.sosmedinstagram.helper.getCurrentTime
import com.example.sosmedinstagram.model.ChatDetailModel
import com.example.sosmedinstagram.model.ChatModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.Random

class ChatDetailViewModel(application: Application): AndroidViewModel(application) {
    private val _getChat = MutableLiveData<List<ChatDetailModel>>()
    val getChat: MutableLiveData<List<ChatDetailModel>> = _getChat
    private val _addChat = MutableLiveData<List<ChatDetailModel>>()
    val addchat: MutableLiveData<List<ChatDetailModel>> = _addChat
    val list = mutableListOf<ChatDetailModel>()
    private val _saveChat = MutableLiveData<List<ChatDetailModel>>()
    val savechat: MutableLiveData<List<ChatDetailModel>> = _saveChat

    fun addChat(context: Context){
        CoroutineScope(Dispatchers.IO).run {
            for (i in 1..10) {
                val chat = ChatDetailModel()
                chat.id = "ChatID${Random().nextInt(100)}"
                chat.sender = "User${Random().nextInt(100)}"
                chat.receiver = "User${Random().nextInt(100)}"
                chat.timestamp = getCurrentDate() +" "+ getCurrentTime()
                chat.isseen = false
                chat.message = "Random Message ${Random().nextInt(100)}"
                //random 1 atau 2
                chat.type = Random().nextInt(2)
                list.add(chat)
            }
            _addChat.postValue(list)
        }
    }
    fun loadChat(context: Context){
        CoroutineScope(Dispatchers.IO).run {
            _getChat.postValue(list)
        }
    }
    fun saveChat(context: Context, chat: ChatDetailModel){
        CoroutineScope(Dispatchers.IO).run {
            list.add(chat)
            _saveChat.postValue(list)
        }
    }
}