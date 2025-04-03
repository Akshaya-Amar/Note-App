package com.amar.mynotes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.amar.mynotes.R

class HomeActivity : AppCompatActivity() {

     private lateinit var navController: NavController

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_home)

          val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
          navController = navHostFragment.navController
          setupActionBarWithNavController(navController)
     }

     override fun onSupportNavigateUp(): Boolean {
          return navController.navigateUp()
     }
}