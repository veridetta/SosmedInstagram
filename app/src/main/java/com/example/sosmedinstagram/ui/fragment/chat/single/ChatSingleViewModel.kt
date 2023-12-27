package com.example.sosmedinstagram.ui.fragment.chat.single

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.helper.getCurrentDate
import com.example.sosmedinstagram.helper.getCurrentTime
import com.example.sosmedinstagram.model.ChatModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.Random

class ChatSingleViewModel(application: Application): AndroidViewModel(application) {
    private val _getChat = MutableLiveData<List<ChatModel>>()
    val getChat: MutableLiveData<List<ChatModel>> = _getChat
    private val _addChat = MutableLiveData<List<ChatModel>>()
    val addchat: MutableLiveData<List<ChatModel>> = _addChat
    val list = mutableListOf<ChatModel>()
    fun addChat(context: Context,type:String){
        CoroutineScope(Dispatchers.IO).run {
            var chadtId = ""
            var sender = ""
            if(type=="single"){
                chadtId = "ChatID${Random().nextInt(100)}"
                sender ="User${Random().nextInt(100)}"
            }else{
                chadtId = "GroupID${Random().nextInt(100)}"
                sender ="Group${Random().nextInt(100)}"
            }
            for (i in 1..10) {
                val chat = ChatModel()
                chat.id = chadtId
                chat.sender = sender
                chat.receiver = sender
                chat.timestamp = getCurrentDate() +" "+ getCurrentTime()
                chat.isseen = false
                chat.lastmessage = "Random Message ${Random().nextInt(100)}"
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
}