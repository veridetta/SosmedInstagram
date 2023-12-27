package com.example.sosmedinstagram.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.adapter.PostAdapter
import com.example.sosmedinstagram.databinding.FragmentHomeBinding
import com.example.sosmedinstagram.databinding.FragmentProfileBinding
import com.example.sosmedinstagram.helper.clearUser
import com.example.sosmedinstagram.ui.fragment.home.HomeFragmentVM
import com.example.sosmedinstagram.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    lateinit var viewModel: ProfileViewModel
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        binding.etEmail.isEnabled = false
        binding.etName.isEnabled = false
        binding.etPhone.isEnabled = false
        binding.etPassword.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getUser.observe(viewLifecycleOwner) { getUser ->
            binding.progressBar.visibility = View.GONE
            binding.etEmail.setText(getUser.email)
            binding.etName.setText(getUser.nama)
            binding.etPhone.setText(getUser.nohp)
            binding.etPassword.setText(getUser.password)
        }
        viewModel.loadUser(requireContext())
        binding.btnUbah.setOnClickListener {
            //enable editable text
            binding.etEmail.isEnabled = true
            binding.etName.isEnabled = true
            binding.etPhone.isEnabled = true
            binding.etPassword.isEnabled = true
            binding.btnUbah.visibility = View.GONE
            binding.btnSimpan.visibility = View.VISIBLE
        }
        binding.btnSimpan.setOnClickListener {
            //save data
            val user = viewModel.getUser.value
            user?.email = binding.etEmail.text.toString()
            user?.nama = binding.etName.text.toString()
            user?.nohp = binding.etPhone.text.toString()
            user?.password = binding.etPassword.text.toString()
            //check kosong
            if (user?.email.isNullOrEmpty()){
                binding.etEmail.error = "Email tidak boleh kosong"
                binding.etEmail.requestFocus()
            }else if (user?.nama.isNullOrEmpty()){
                binding.etName.error = "Nama tidak boleh kosong"
                binding.etName.requestFocus()
            }else if (user?.nohp.isNullOrEmpty()){
                binding.etPhone.error = "No Hp tidak boleh kosong"
                binding.etPhone.requestFocus()
            }else if (user?.password.isNullOrEmpty()){
                binding.etPassword.error = "Password tidak boleh kosong"
                binding.etPassword.requestFocus()
            }else{
                binding.progressBar.visibility = View.VISIBLE
                viewModel.saveUser.observe(viewLifecycleOwner) { saveUser ->
                    binding.progressBar.visibility = View.GONE
                    binding.etEmail.isEnabled = false
                    binding.etName.isEnabled = false
                    binding.etPhone.isEnabled = false
                    binding.etPassword.isEnabled = false
                    binding.btnUbah.visibility = View.VISIBLE
                    binding.btnSimpan.visibility = View.GONE
                }
                viewModel.postUser(requireContext(), user!!)
            }
        }
        binding.btnLogout.setOnClickListener {
            clearUser(requireContext() )
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}