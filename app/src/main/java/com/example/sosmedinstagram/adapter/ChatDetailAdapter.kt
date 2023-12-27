package com.example.sosmedinstagram.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sosmedinstagram.databinding.ItemChatBinding
import com.example.sosmedinstagram.databinding.ItemChatDetailBinding
import com.example.sosmedinstagram.databinding.ItemChatDetailRightBinding
import com.example.sosmedinstagram.model.ChatDetailModel
import com.example.sosmedinstagram.ui.fragment.chat.single.detail.ChatDetailActivity

class ChatDetailAdapter : ListAdapter<ChatDetailModel, ChatDetailAdapter.MyViewHolder>(DIFF_CALLBACK) {
    interface OnItemClickListener {
        //fun onItemClick(post: ChatDetailModel)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemChatDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewType, binding,
            ItemChatDetailRightBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    class MyViewHolder(val tipe:Int, val binding: ItemChatDetailBinding,  val bindingRight :ItemChatDetailRightBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: ChatDetailModel) {
            if (tipe == 1){
                with(binding){
                    tvPesan.text = post.message
                }
            }else{
                with(bindingRight){
                    tvPesan.text = post.message
                }
            }

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChatDetailModel>() {
            override fun areItemsTheSame(oldItem: ChatDetailModel, newItem: ChatDetailModel): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ChatDetailModel, newItem: ChatDetailModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}