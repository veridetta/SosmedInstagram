package com.example.sosmedinstagram.ui.fragment.chat.single.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sosmedinstagram.adapter.ChatDetailAdapter
import com.example.sosmedinstagram.databinding.ActivityChatDetailBinding
import com.example.sosmedinstagram.helper.getCurrentDate
import com.example.sosmedinstagram.helper.getCurrentTime
import com.example.sosmedinstagram.model.ChatDetailModel
import java.util.Random

class ChatDetailActivity : AppCompatActivity() {
    lateinit var viewModel: ChatDetailViewModel
    lateinit var binding : ActivityChatDetailBinding
    lateinit var adapter: ChatDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ChatDetailViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvChat.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvChat.addItemDecoration(itemDecoration)

        adapter = ChatDetailAdapter()
        binding.rvChat.adapter = adapter
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getChat.observe(this) { getPost ->
            adapter.submitList(getPost)
            binding.progressBar.visibility = View.GONE
        }
        viewModel.addchat.observe(this) { addList ->
            viewModel.loadChat(this)
        }
        viewModel.addChat(this)
        binding.btnSend.setOnClickListener {
            val chat = ChatDetailModel()
            chat.id = "ChatID${Random().nextInt(100)}"
            chat.sender = "User${Random().nextInt(100)}"
            chat.receiver = "User${Random().nextInt(100)}"
            chat.timestamp = getCurrentDate() + " " + getCurrentTime()
            chat.isseen = false
            chat.message = binding.etChat.text.toString()
            chat.type = 1
            binding.progressBar.visibility = View.VISIBLE
            viewModel.savechat.observe(this) { saveChat ->
                binding.etChat.setText("")
                binding.progressBar.visibility = View.GONE
                adapter.submitList(saveChat)
                adapter.notifyDataSetChanged()
            }
            viewModel.saveChat(this, chat)
        }
    }
}