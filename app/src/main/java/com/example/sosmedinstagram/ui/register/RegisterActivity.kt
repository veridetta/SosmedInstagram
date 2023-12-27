package com.example.sosmedinstagram.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.databinding.ActivityRegisterBinding
import com.example.sosmedinstagram.helper.saveUser
import com.example.sosmedinstagram.model.UserModel
import com.example.sosmedinstagram.ui.home.HomeActivity
import com.example.sosmedinstagram.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var registerViewModel: RegisterViewModel
    lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }
    private fun initView(){
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun initListener(){
        binding.btnRegister.setOnClickListener {
            if(binding.etEmail.text.toString().isNotEmpty() &&
                binding.etPassword.text.toString().isNotEmpty() &&
                binding.etName.text.toString().isNotEmpty() &&
                binding.etPhone.text.toString().isNotEmpty()){
                val user: UserModel = UserModel()
                user.email = binding.etEmail.text.toString()
                user.password = binding.etPassword.text.toString()
                user.nama = binding.etName.text.toString()
                user.nohp = binding.etPhone.text.toString()
                user.isLogin = true

                val saveUser = saveUser(user,this)
                binding.progressBar.visibility = android.view.View.VISIBLE
                registerViewModel.isRegister.observe(this){
                    if(it){
                        binding.progressBar.visibility = android.view.View.GONE
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                }
                registerViewModel.checkRegister(this)
            }else{
                binding.etEmail.error = "Email tidak boleh kosong"
                binding.etPassword.error = "Password tidak boleh kosong"
                binding.etName.error = "Nama tidak boleh kosong"
                binding.etPhone.error = "No HP tidak boleh kosong"
            }
        }
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}