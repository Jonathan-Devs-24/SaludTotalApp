package com.example.saludtotalapp.ViewModel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.saludtotalapp.Model.Usuario
import com.example.saludtotalapp.Network.RetrofitClient
import com.example.saludtotalapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializo ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción del botón "Registrarse"
        binding.Registro.setOnClickListener {
            val item = Intent(this, RegisterActivity::class.java)
            startActivity(item)
        }
    }
}
