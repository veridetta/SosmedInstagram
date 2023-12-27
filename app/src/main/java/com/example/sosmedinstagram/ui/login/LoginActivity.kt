package com.example.sosmedinstagram.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.databinding.ActivityLoginBinding
import com.example.sosmedinstagram.helper.saveUser
import com.example.sosmedinstagram.model.UserModel
import com.example.sosmedinstagram.ui.home.HomeActivity
import com.example.sosmedinstagram.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }
    private fun initView(){
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun initListener(){
        binding.btnLogin.setOnClickListener {
            if(binding.etEmail.text.toString().isNotEmpty() && binding.etPassword.text.toString().isNotEmpty()){
                val user: UserModel = UserModel()
                user.email = binding.etEmail.text.toString()
                user.password = binding.etPassword.text.toString()
                val saveUser = saveUser(user,this)
                binding.progressBar.visibility = android.view.View.VISIBLE
                loginViewModel.isLogin.observe(this){
                    if(it){
                        binding.progressBar.visibility = android.view.View.GONE
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                    binding.progressBar.visibility = android.view.View.GONE
                }
                loginViewModel.checkLogin(this)
            }else{
                binding.etEmail.error = "Email tidak boleh kosong"
                binding.etPassword.error = "Password tidak boleh kosong"
            }
        }
        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}