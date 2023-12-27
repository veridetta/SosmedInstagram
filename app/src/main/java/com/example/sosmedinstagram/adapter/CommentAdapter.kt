package com.example.sosmedinstagram.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sosmedinstagram.databinding.ItemCommentBinding
import com.example.sosmedinstagram.model.CommentModel

class CommentAdapter : ListAdapter<CommentModel, CommentAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        holder.itemView.setOnClickListener {
            listener?.onItemClick(user)
        }
    }

    class MyViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment:CommentModel) {
            binding.tvNama.text = comment.nama
            binding.tvComment.text = comment.comment
            val datetime = comment.date+" "+comment.time
            binding.tvDate.text = datetime
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: CommentModel)
    }
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommentModel>() {
            override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}