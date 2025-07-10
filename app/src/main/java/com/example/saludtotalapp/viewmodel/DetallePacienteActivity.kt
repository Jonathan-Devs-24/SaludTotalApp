package com.example.saludtotalapp.viewmodel


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saludtotalapp.databinding.ActivityDetallePacienteBinding
import com.example.saludtotalapp.utils.FavoriteManager
import org.json.JSONObject

class DetallePacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallePacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener datos del intent
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val dni = intent.getIntExtra("dni", 0)
        val afiliacion = intent.getStringExtra("afiliacion") ?: ""
        val observaciones = intent.getStringExtra("observaciones") ?: ""

        // Mostrar en los TextView
        binding.tvNombreCompleto.text = "$nombre $apellido"
        binding.tvDni.text = "DNI: $dni"
        binding.tvAfiliacion.text = "Afiliado: $afiliacion"
        binding.tvObservaciones.text = "Observaciones: $observaciones"

        val favManager = FavoriteManager(this)

        val jsonPaciente = JSONObject().apply {
            put("nombre", nombre)
            put("apellido", apellido)
            put("dni", dni)
            put("afiliacion", afiliacion)
            put("observaciones", observaciones)
        }

        // Mostrar texto correcto según si ya es favorito
        if (favManager.esFavorito(dni)) {
            binding.btnFavorito.text = "Quitar de favoritos"
        }

        binding.btnFavorito.setOnClickListener {
            if (favManager.esFavorito(dni)) {
                favManager.quitarFavorito(dni)
                Toast.makeText(this, "Quitado de favoritos", Toast.LENGTH_SHORT).show()
                binding.btnFavorito.text = "Agregar a favoritos"
            } else {
                favManager.agregarFavorito(jsonPaciente)
                Toast.makeText(this, "Agregado a favoritos", Toast.LENGTH_SHORT).show()
                binding.btnFavorito.text = "Quitar de favoritos"
            }
        }

    }
}
