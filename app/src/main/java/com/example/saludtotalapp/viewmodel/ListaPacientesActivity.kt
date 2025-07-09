package com.example.saludtotalapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saludtotalapp.network.RetrofitClient
import com.example.saludtotalapp.databinding.ActivityListaPacientesBinding
import com.example.saludtotalapp.model.Paciente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaPacientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaPacientesBinding
    private val api = RetrofitClient.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaPacientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarPacientes()
    }

    private fun cargarPacientes() {
        api.getPacientes().enqueue(object : Callback<List<Paciente>> {
            override fun onResponse(call: Call<List<Paciente>>, response: Response<List<Paciente>>) {
                if (response.isSuccessful) {
                    val pacientes = response.body() ?: emptyList()
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ListaPacientesActivity)
                        adapter = PacienteAdapter(pacientes)
                    }
                } else {
                    Toast.makeText(this@ListaPacientesActivity, "Error al obtener pacientes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Paciente>>, t: Throwable) {
                Toast.makeText(this@ListaPacientesActivity, "Error de red: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}

