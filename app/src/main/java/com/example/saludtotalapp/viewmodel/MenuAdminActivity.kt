package com.example.saludtotalapp.viewmodel


import com.example.saludtotalapp.model.Paciente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.saludtotalapp.network.RetrofitClient
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saludtotalapp.databinding.ActivityMenuAdminBinding
import com.example.saludtotalapp.view.PacienteAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager


class MenuAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuAdminBinding
    private val api = RetrofitClient.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarPacientes()
    }

    private fun cargarPacientes() {
        api.getPacientes().enqueue(object : retrofit2.Callback<List<Paciente>> {
            override fun onResponse(call: retrofit2.Call<List<Paciente>>, response: retrofit2.Response<List<Paciente>>) {
                if (response.isSuccessful) {
                    val pacientes = response.body() ?: emptyList()
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MenuAdminActivity)
                        adapter = PacienteAdapter(pacientes)
                    }
                } else {
                    Toast.makeText(this@MenuAdminActivity, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Paciente>>, t: Throwable) {
                Toast.makeText(this@MenuAdminActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}