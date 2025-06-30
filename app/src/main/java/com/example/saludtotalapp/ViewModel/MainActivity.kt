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
            val item = Intent(this, RegistroActivity::class.java)
            startActivity(item)
        }

        // Llamamos a la función para probar el consumo de la API
        obtenerUsuarios()
    }

    private fun obtenerUsuarios() {
        val call = RetrofitClient.instance.getUsuarios()
        call.enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful) {
                    val usuarios = response.body()
                    usuarios?.forEach {
                        Log.d("API", "Usuario: ${it.nombre} ${it.apellido}")
                    }
                } else {
                    Log.e("API", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                Log.e("API", "Error de conexión: ${t.message}")
            }
        })
    }
}
