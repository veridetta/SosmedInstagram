package com.example.sosmedinstagram.ui.fragment.post

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sosmedinstagram.databinding.FragmentPostBinding
import com.example.sosmedinstagram.helper.showSnackbar
import com.example.sosmedinstagram.model.PostModel
import com.example.sosmedinstagram.ui.home.HomeActivity

class PostFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 123
    private val CAMERA_PERMISSION_REQUEST_CODE = 456

    lateinit var viewModel: PostViewModel
    lateinit var binding: FragmentPostBinding
    private lateinit var imgUri : Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PostViewModel::class.java]
        binding.btnUpload.setOnClickListener {
            checkGalleryPermission()
        }
        binding.btnPost.setOnClickListener {
            if(binding.etCaption.text.toString().isNotEmpty() && imgUri != null) {
                val caption = binding.etCaption.text.toString()
                val post = PostModel()
                post.caption = caption
                post.image = binding.ivSelected.toString()
                binding.progressBar.visibility = View.VISIBLE
                viewModel.sendPost.observe(viewLifecycleOwner) {
                    binding.progressBar.visibility = View.GONE
                    showSnackbar(requireContext(), "Post Berhasil Ditambahkan")
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                }
                viewModel.uploadPost(requireContext(), post)
            }
        }
    }

    private fun checkGalleryPermission() {
        val galleryPermission = Manifest.permission.READ_EXTERNAL_STORAGE

        if (isPermissionGranted(galleryPermission)) {
            openGallery()
        } else {
            requestPermission(galleryPermission, CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(permission),
            requestCode
        )
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Handle the selected image
            imgUri = data.data!!
            // Do something with the selected image URI
            Glide.with(this)
                .load(imgUri)
                .into(binding.ivSelected)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                // Handle permission denied
                showSnackbar(requireContext(),"Permission denied")
            }
        }
    }
}
