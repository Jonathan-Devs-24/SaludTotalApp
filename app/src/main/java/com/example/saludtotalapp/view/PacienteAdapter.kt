package com.example.saludtotalapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saludtotalapp.model.Paciente
import com.example.saludtotalapp.databinding.ItemPacienteBinding

class PacienteAdapter(private val lista: List<Paciente>) :
    RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>() {

    inner class PacienteViewHolder(val binding: ItemPacienteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val binding = ItemPacienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PacienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = lista[position]
        with(holder.binding) {
            tvNombre.text = "${paciente.usuario.nombre} ${paciente.usuario.apellido}"
            tvDni.text = "DNI: ${paciente.usuario.dni}"
            tvAfiliacion.text = "Afiliado: ${paciente.numeroAfliado}"
        }
    }

    override fun getItemCount() = lista.size
}
