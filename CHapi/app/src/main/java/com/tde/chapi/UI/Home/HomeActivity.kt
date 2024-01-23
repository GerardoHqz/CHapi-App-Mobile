package com.tde.chapi.UI.Home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.tde.chapi.R
import com.tde.chapi.databinding.ActivityHomeBinding
import com.tde.chapi.viewmodel.ViewModelConsultation
import com.tde.chapi.viewmodel.ViewModelForgotPassword
import com.tde.chapi.viewmodel.ViewModelRemind

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModelRemind: ViewModelRemind by viewModels()
    private val viewModelConsultation : ViewModelConsultation by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_content_home) as NavHostFragment
        var navController = navHostFragment.navController

    }

}