package com.example.sosmedinstagram.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sosmedinstagram.R
import com.example.sosmedinstagram.databinding.ActivityHomeBinding
import com.example.sosmedinstagram.helper.showConfirmationDialog
import com.example.sosmedinstagram.ui.fragment.chat.ChatFragment
import com.example.sosmedinstagram.ui.fragment.home.HomeFragment
import com.example.sosmedinstagram.ui.fragment.post.PostFragment
import com.example.sosmedinstagram.ui.fragment.profile.ProfileFragment

class HomeActivity : AppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initFragment()
        initListener()
    }
    private fun initView(){
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun initFragment(){
        val fragment = intent.getStringExtra("fragment")
        if(fragment != null){
            if(fragment == "chat"){
                replaceFragment(ChatFragment())
            }else if(fragment == "profile"){
                replaceFragment(ProfileFragment())
            }else if(fragment == "home"){
                replaceFragment(HomeFragment())
            }
        }else{
            replaceFragment(HomeFragment())
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
    private fun initListener(){
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_chat -> {
                    replaceFragment(ChatFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_profile -> {
                    replaceFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_add -> {
                    replaceFragment(PostFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    //on back pressed
    override fun onBackPressed() {
        super.onBackPressed()
        showConfirmationDialog(this)
    }
}