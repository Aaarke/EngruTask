package com.example.engurutask.base

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.engurutask.R
import com.example.engurutask.utility.CheckNetwork
import com.example.engurutask.utility.GlobalVars

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setActionBarColor()
        val network = CheckNetwork(applicationContext)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            network.registerDefaultNetworkCallback()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                network.registerNetworkCallback()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        makeToast()
    }
    private fun setActionBarColor(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.actionbar)
    }

    fun makeToast(){
        if (!GlobalVars.isNetworkConnected) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
        }
    }
}