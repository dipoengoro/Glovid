package com.dicoding.myrecyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.myrecyclerview.databinding.ActivityMainBinding
import com.dicoding.myrecyclerview.ui.HomeFragment
import com.dicoding.myrecyclerview.ui.NewsFragment
import com.dicoding.myrecyclerview.ui.ProvincesFragment
import com.dicoding.myrecyclerview.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyRecyclerView)
        ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bottomNavigation.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.fragment_home -> setCurrentFragment(HomeFragment())
                    R.id.fragment_news -> setCurrentFragment(NewsFragment())
                }
                true
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()

    }

    override fun onBackPressed() {
        if (binding.bottomNavigation.selectedItemId == R.id.fragment_news) {
            setCurrentFragment(HomeFragment())
            binding.bottomNavigation.selectedItemId = R.id.fragment_home
        } else {
            super.onBackPressed()
        }
    }
}