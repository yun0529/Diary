package com.example.mydiary.src.main

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.mydiary.R
import com.example.mydiary.config.BaseActivity
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.src.main.list.ListFragment
import com.example.mydiary.src.main.statistics.StatisticsFragment
import com.example.mydiary.src.main.write.WriteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding :: inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_fl_fragment, ListFragment()).commitAllowingStateLoss()

        binding.mainBnv.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_main_list -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_fl_fragment, ListFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.item_main_write -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_fl_fragment, WriteFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.item_main_statistics -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_fl_fragment, StatisticsFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })
    }
    @SuppressLint("ResourceType")
    fun fragmentChange(fragmentNum : Int){
        if(fragmentNum == 0) {
            supportFragmentManager.beginTransaction().replace(R.id.main_fl_fragment, ListFragment()).commitAllowingStateLoss()
            binding.mainBnv.selectedItemId = R.id.item_main_list
        } else if(fragmentNum == 1) {
            supportFragmentManager.beginTransaction().replace(R.id.main_fl_fragment, WriteFragment()).commitAllowingStateLoss()
            binding.mainBnv.selectedItemId = R.id.item_main_write
        }

    }
}