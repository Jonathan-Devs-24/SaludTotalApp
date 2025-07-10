package com.example.saludtotalapp.viewmodel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saludtotalapp.databinding.ActivityMenuAdminBinding
import com.example.saludtotalapp.model.Paciente
import com.example.saludtotalapp.network.RetrofitClient
import com.example.saludtotalapp.view.PacienteAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.saludtotalapp.R




class MenuAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuAdminBinding
    private val api = RetrofitClient.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarPacientes()

        // Configuración del menú de navegación inferior
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inicio -> {
                    true
                }
                R.id.nav_favorite -> {
                    val intent = Intent(this, FavoritosActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun cargarPacientes() {
        api.getPacientes().enqueue(object : Callback<List<Paciente>> {
            override fun onResponse(
                call: Call<List<Paciente>>,
                response: Response<List<Paciente>>
            ) {
                if (response.isSuccessful) {
                    val pacientes = response.body() ?: emptyList()

                    // Usamos el adapter con lambda para eliminar y ver
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MenuAdminActivity)
                        adapter = PacienteAdapter(
                            pacientes,
                            onEliminarClick = { paciente -> eliminarPaciente(paciente) },
                            onVerClick = { paciente -> verPaciente(paciente) }
                        )
                    }


                } else {
                    Toast.makeText(this@MenuAdminActivity, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Paciente>>, t: Throwable) {
                Toast.makeText(this@MenuAdminActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun eliminarPaciente(paciente: Paciente) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar paciente")
            .setMessage("¿Estás seguro de que querés eliminar a ${paciente.usuario.nombre}?")
            .setPositiveButton("Sí") { _, _ ->
                api.eliminarPaciente(paciente.idPaciente).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@MenuAdminActivity, "Paciente eliminado", Toast.LENGTH_SHORT).show()
                            cargarPacientes()
                        } else {
                            Toast.makeText(this@MenuAdminActivity, "Error al eliminar", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(this@MenuAdminActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun verPaciente(paciente: Paciente) {
        val intent = Intent(this, DetallePacienteActivity::class.java)
        intent.putExtra("nombre", paciente.usuario.nombre)
        intent.putExtra("apellido", paciente.usuario.apellido)
        intent.putExtra("dni", paciente.usuario.dni)
        intent.putExtra("afiliacion", paciente.numeroAfliado)
        intent.putExtra("observaciones", paciente.observacion ?: "")
        startActivity(intent)
    }
}
