package com.dicoding.myrecyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyRecyclerView)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_home -> setCurrentFragment(HomeFragment())
                R.id.fragment_provinces -> setCurrentFragment(ProvincesFragment())
                R.id.fragment_news -> setCurrentFragment(ProvincesFragment())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}