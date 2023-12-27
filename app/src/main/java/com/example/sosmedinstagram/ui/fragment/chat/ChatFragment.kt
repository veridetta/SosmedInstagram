package com.example.sosmedinstagram.ui.fragment.chat

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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ChatFragment : Fragment() {

    lateinit var viewModel: ChatViewModel
    lateinit var binding: FragmentChatBinding
    var tipe ="single"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        initViewPager()
    }
    private fun initViewPager(){
        val sectionsPagerAdapter = PagerAdapter(requireActivity(), tipe)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Private Chat"
                1 -> tab.text = "Group Chat"
            }
        }.attach()
    }
}