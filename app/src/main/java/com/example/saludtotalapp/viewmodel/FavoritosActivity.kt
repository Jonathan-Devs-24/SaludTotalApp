package com.example.saludtotalapp.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saludtotalapp.databinding.ActivityFavoritosBinding
import com.example.saludtotalapp.utils.FavoriteManager
import org.json.JSONObject
import com.example.saludtotalapp.view.FavoritoAdapter

class FavoritosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favManager = FavoriteManager(this)
        val lista = favManager.obtenerFavoritos()

        val listaConvertida = lista.map {
            JSONObject(it.toString())
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = FavoritoAdapter(listaConvertida)
    }
}
