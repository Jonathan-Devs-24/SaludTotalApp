package com.example.saludtotalapp.viewmodel

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.saludtotalapp.databinding.ActivityMenu1Binding

class Menu1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMenu1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenu1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdmin.setOnClickListener {
            val intent = Intent (this, MenuAdminActivity::class.java)
            startActivity(intent)
        }
    }
}