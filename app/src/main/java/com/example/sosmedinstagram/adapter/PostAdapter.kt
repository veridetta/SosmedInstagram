package com.example.sosmedinstagram.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.databinding.ItemPostBinding
import com.example.sosmedinstagram.helper.savePost
import com.example.sosmedinstagram.helper.showSnackbar
import com.example.sosmedinstagram.model.PostModel
import com.example.sosmedinstagram.ui.post.PostDetailActivity

class PostAdapter : ListAdapter<PostModel, PostAdapter.MyViewHolder>(DIFF_CALLBACK) {
    interface OnItemClickListener {
        fun onLikeClick(post: PostModel)
        fun onCommentClick(post: PostModel)
        fun onShareClick(post: PostModel)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)

        holder.binding.imgLike.setOnClickListener {
            val like = post.like?.toInt()?.plus(1)
            holder.binding.tvLike.text = like.toString()
            listener?.onLikeClick(post)
            holder.binding.imgLike.setImageResource(R.drawable.ic_like)
        }

        holder.binding.imgComment.setOnClickListener {
            listener?.onCommentClick(post)
            //intent
            val intent = Intent(holder.itemView.context, PostDetailActivity::class.java)
            savePost(holder.itemView.context, post)
            holder.itemView.context.startActivity(intent)
        }

        holder.binding.imgShare.setOnClickListener {
            listener?.onShareClick(post)
            showSnackbar(holder.itemView.context, "Shared")
        }

    }

    class MyViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post:PostModel) {
                Glide.with(binding.root.context)
                    .load(post.image)
                    .into(binding.imageViewPost)
                binding.tvNama.text = post.nama
                binding.tvCaption.text = post.caption
                binding.tvLike.text = post.like.toString()
                binding.tvComment.text = post.comment.toString()
                binding.tvShare.text = post.share.toString()
            val dateTime = post.date+" "+post.time
            binding.tvDate.text = dateTime

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostModel>() {
            override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}