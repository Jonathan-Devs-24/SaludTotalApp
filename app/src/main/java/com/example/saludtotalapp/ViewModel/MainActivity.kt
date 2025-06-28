package com.example.saludtotalapp.ViewModel

import android.os.Bundle
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.saludtotalapp.R
import com.example.saludtotalapp.databinding.ActivityMainBinding
import com.example.saludtotalapp.databinding.RegistroBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializo ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción del botón "Registrarse"
        binding.Registro.setOnClickListener {
            val item = Intent(this, RegistroActivity::class.java)
            startActivity(item)
        }

    }
}