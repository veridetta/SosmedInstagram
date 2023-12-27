package com.example.sosmedinstagram.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.databinding.ItemChatBinding
import com.example.sosmedinstagram.helper.savePost
import com.example.sosmedinstagram.helper.showSnackbar
import com.example.sosmedinstagram.model.ChatModel
import com.example.sosmedinstagram.ui.fragment.chat.single.detail.ChatDetailActivity
import com.example.sosmedinstagram.ui.post.PostDetailActivity

class ChatAdapter : ListAdapter<ChatModel, ChatAdapter.MyViewHolder>(DIFF_CALLBACK) {
    interface OnItemClickListener {
        fun onItemClick(post: ChatModel)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)

        holder.binding.cardView.setOnClickListener {
            listener?.onItemClick(post)
            //intent
            val intent = Intent(holder.itemView.context, ChatDetailActivity::class.java)
            intent.putExtra("id", post.id)
            holder.itemView.context.startActivity(intent)
        }

    }

    class MyViewHolder(val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post:ChatModel) {
            with(binding){
                tvNama.text = post.sender
                tvPesan.text = post.lastmessage
                tvTanggal.text = post.timestamp
                if(post.isseen == true){
                    tvStatus.text = "Dilihat"
                }else{
                    tvStatus.text = "Belum Dilihat"
                }

            }

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChatModel>() {
            override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}