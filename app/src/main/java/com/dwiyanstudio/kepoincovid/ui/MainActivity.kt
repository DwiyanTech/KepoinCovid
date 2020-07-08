package com.dwiyanstudio.kepoincovid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.dwiyanstudio.kepoincovid.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        footer_nav.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_view,
            HomeFragment()
        ).commit()

    }

    private fun fragmentIdentifier(fragment:Fragment) : Boolean{
        supportFragmentManager.beginTransaction().replace(R.id.fragment_view,fragment).commit()
        return true
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentClass : Fragment = when(item.itemId){
            R.id.home_item -> HomeFragment()
            R.id.info_item -> NewsFragment()
            R.id.save_item -> SaveFragment()
            else -> HomeFragment()
        }
        return fragmentIdentifier(fragmentClass)
    }



}