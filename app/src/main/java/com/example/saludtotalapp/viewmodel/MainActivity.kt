package com.example.saludtotalapp.viewmodel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saludtotalapp.databinding.ActivityMainBinding
import com.example.saludtotalapp.network.RetrofitClient
import com.example.saludtotalapp.model.LoginDTO
import com.example.saludtotalapp.model.LoginResponse
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

        // Acción del botón "Registrarse" (lleva al formulario de registro)
        binding.Registro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Acción del botón "Iniciar sesión" (inicia sesion y lleva la panel de menu)
        binding.btnLogin.setOnClickListener {
            val loginDTO = LoginDTO(
                correo = binding.etCorreo.text.toString(),
                contrasenia = binding.etContrasenia.text.toString()
            )

            RetrofitClient.instance.login(loginDTO).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        Toast.makeText(this@MainActivity, "Bienvenido ${user?.nombre}", Toast.LENGTH_SHORT).show()

                        // Navegar al panel Menu1Activity
                        val intent = Intent(this@MainActivity, Menu1Activity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@MainActivity, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
