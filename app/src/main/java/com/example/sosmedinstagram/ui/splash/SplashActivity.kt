package com.example.sosmedinstagram.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.sosmedinstagram.MainActivity
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.databinding.ActivitySplashBinding
import com.example.sosmedinstagram.ui.home.HomeActivity
import com.example.sosmedinstagram.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    lateinit var splashViewModel: SplashViewModel
    lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObserve()
    }
    fun initView(){
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun initObserve() {
        binding.progressBar.visibility = View.VISIBLE
        splashViewModel.isLogin.observe(this) {
            if (it) {
                binding.progressBar.visibility = View.GONE
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                binding.progressBar.visibility = View.GONE
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        splashViewModel.checkLogin(this)
    }
}