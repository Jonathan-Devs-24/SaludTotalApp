package com.example.saludtotalapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saludtotalapp.databinding.ItemFavoritoBinding
import org.json.JSONObject

class FavoritoAdapter(private val lista: List<JSONObject>) :
    RecyclerView.Adapter<FavoritoAdapter.FavoritoViewHolder>() {

    inner class FavoritoViewHolder(val binding: ItemFavoritoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        val binding = ItemFavoritoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        val paciente = lista[position]

        with(holder.binding) {
            tvNombre.text = "${paciente.getString("nombre")} ${paciente.getString("apellido")}"
            tvDni.text = "DNI: ${paciente.getInt("dni")}"
            tvAfiliacion.text = "Afiliado: ${paciente.getString("afiliacion")}"
            tvObservaciones.text = "Observaciones: ${paciente.getString("observaciones")}"
        }
    }

    override fun getItemCount() = lista.size
}
