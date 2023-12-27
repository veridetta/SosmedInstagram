package com.example.sosmedinstagram.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sosmedinstagram.ui.fragment.chat.ChatFragment
import com.example.sosmedinstagram.ui.fragment.chat.single.ChatSingleFragment

class PagerAdapter(activity: FragmentActivity, private val type:String)
    : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = ChatSingleFragment()
        val args = Bundle().apply {
            putString(ChatSingleFragment.ARG_TYPE, type)
            putInt(ChatSingleFragment.ARG_POSITION, position + 1)
        }
        fragment.arguments = args
        return fragment
    }
    override fun getItemCount(): Int {
        return 2
    }
}