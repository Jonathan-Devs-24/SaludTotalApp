package com.example.saludtotalapp.viewmodel

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saludtotalapp.model.Usuario
import com.example.saludtotalapp.network.RetrofitClient
import com.example.saludtotalapp.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent


class RegisterActivity : AppCompatActivity() {

    // Declaramos ViewBinding
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuramos el spinner de roles
        val roles = listOf("Paciente", "Profesional")
        binding.spinnerRol.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

        // Botón de registro
        binding.button3.setOnClickListener {
            if (validarCampos()) {
                registrarUsuario()
            }
        }
    }

    // Valida que no haya campos vacíos
    private fun validarCampos(): Boolean {
        val campos = listOf(
            binding.etNombre.text,
            binding.etApellido.text,
            binding.etDni.text,
            binding.etCorreo.text,
            binding.etTelefono.text,
            binding.etPassword2.text
        )

        if (campos.any { it.isNullOrBlank() }) {
            Toast.makeText(this, "Completá todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    // Enviar datos a la API usando Retrofit
    private fun registrarUsuario() {
        val usuario = Usuario(
            nombre = binding.etNombre.text.toString(),
            apellido = binding.etApellido.text.toString(),
            dni = binding.etDni.text.toString().toInt(),
            correo = binding.etCorreo.text.toString(),
            telefono = binding.etTelefono.text.toString().toLong(),
            contrasenia = binding.etPassword2.text.toString(),
            rol = binding.spinnerRol.selectedItem.toString()
        )

        RetrofitClient.instance.registrarUsuario(usuario).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    limpiarCampos()

                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Fallo la conexión: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        })
    }

    // Limpia los campos
    private fun limpiarCampos() {
        binding.etNombre.text.clear()
        binding.etApellido.text.clear()
        binding.etDni.text.clear()
        binding.etCorreo.text.clear()
        binding.etTelefono.text.clear()
        binding.etPassword2.text.clear()
        binding.spinnerRol.setSelection(0)
    }
}
