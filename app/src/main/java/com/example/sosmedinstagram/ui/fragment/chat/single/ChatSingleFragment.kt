package com.example.sosmedinstagram.ui.fragment.chat.single

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.sosmedinstagram.adapter.ChatAdapter
import com.example.sosmedinstagram.adapter.PagerAdapter
import com.example.sosmedinstagram.adapter.PostAdapter
import com.example.sosmedinstagram.databinding.FragmentChatBinding
import com.example.sosmedinstagram.databinding.FragmentChatSingleBinding
import com.example.sosmedinstagram.ui.fragment.chat.ChatViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ChatSingleFragment : Fragment() {
    companion object {
        const val ARG_TYPE = "single"
        const val ARG_POSITION = "position"
    }
    lateinit var viewModel: ChatSingleViewModel
    lateinit var binding: FragmentChatSingleBinding
    lateinit var adapter: ChatAdapter
    private var type = ""
    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatSingleBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ChatSingleViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvChat.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvChat.addItemDecoration(itemDecoration)

        adapter = ChatAdapter()
        binding.rvChat.adapter = adapter
        binding.progressBar2.visibility = View.VISIBLE

        viewModel.getChat.observe(viewLifecycleOwner) { getPost ->
            adapter.submitList(getPost)
            binding.progressBar2.visibility = View.GONE
        }
        viewModel.addchat.observe(viewLifecycleOwner) { addList ->
            viewModel.loadChat(requireContext())

        }
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            type = it.getString(ARG_TYPE) ?: ""
        }
        if (position == 1){
            type = "single"
        }else{
            type = "group"
        }
        viewModel.addChat(requireContext(),type)
    }
}