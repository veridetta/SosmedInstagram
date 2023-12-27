package com.example.sosmedinstagram.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.adapter.PostAdapter
import com.example.sosmedinstagram.databinding.FragmentHomeBinding
import com.example.sosmedinstagram.ui.home.HomeViewModel


class HomeFragment : Fragment() {

    lateinit var viewModel: HomeFragmentVM
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeFragmentVM::class.java]

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvPost.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvPost.addItemDecoration(itemDecoration)

        adapter = PostAdapter()
        binding.rvPost.adapter = adapter
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getPost.observe(viewLifecycleOwner) { getPost ->
            adapter.submitList(getPost)
            binding.progressBar.visibility = View.GONE
        }
        viewModel.addList.observe(viewLifecycleOwner) { addList ->
            viewModel.loadPost(requireContext())

        }
        viewModel.addPost(requireContext())
    }
}