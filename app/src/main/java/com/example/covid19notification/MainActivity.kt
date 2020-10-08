package com.example.covid19notification

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        Log.d(tag, "onCreate called.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume called.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause() called.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop() called.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy() called. ")
    }



}