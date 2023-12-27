package com.example.sosmedinstagram.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.adapter.CommentAdapter
import com.example.sosmedinstagram.adapter.PostAdapter
import com.example.sosmedinstagram.databinding.ActivityPostDetailBinding
import com.example.sosmedinstagram.helper.showSnackbar

class PostDetailActivity : AppCompatActivity() {
    lateinit var viewModel: PostDetailVM
    lateinit var binding : ActivityPostDetailBinding
    lateinit var adapter: CommentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[PostDetailVM::class.java]
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvComment.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvComment.addItemDecoration(itemDecoration)

        adapter = CommentAdapter()
        binding.rvComment.adapter = adapter
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getComment.observe(this) { getComment ->
            adapter.submitList(getComment)
            binding.progressBar.visibility = View.GONE
        }
        viewModel.addComment.observe(this) { addList ->
            viewModel.loadComment(this)

        }
        viewModel.getPost.observe(this) { getPost ->
            binding.tvNama.text = getPost.nama
            binding.tvCaption.text = getPost.caption
            binding.tvLike.text = getPost.like.toString()
            binding.tvComment.text = getPost.comment.toString()
            val datetime = getPost.date+" "+getPost.time
            binding.tvDate.text = datetime
            binding.tvShare.text = getPost.share.toString()
            Glide.with(this)
                .load(getPost.image)
                .into(binding.imageViewPost)
        }
        viewModel.setPost(this)
        viewModel.addcomment(this)
        binding.btnSend.setOnClickListener {
            if(binding.etComment.text.toString() != ""){
                viewModel.postcomment.observe(this) { postComment ->
                    adapter.submitList(postComment)
                    adapter.notifyDataSetChanged()
                    binding.etComment.setText("")
                    showSnackbar(this, "Comment Posted")
                }
                viewModel.postComment(this, binding.etComment.text.toString())
            }else{
                showSnackbar(this, "Comment cannot be empty")
            }
        }
    }

}