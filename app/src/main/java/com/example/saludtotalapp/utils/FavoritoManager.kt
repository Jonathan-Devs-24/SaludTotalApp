package com.example.saludtotalapp.utils

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class FavoriteManager(context: Context) {

    private val prefs = context.getSharedPreferences("favorite_prefs", Context.MODE_PRIVATE)

    fun agregarFavorito(paciente: JSONObject) {
        val lista = obtenerFavoritos().toMutableList()
        lista.add(paciente)
        guardarLista(lista)
    }

    fun quitarFavorito(dni: Int) {
        val lista = obtenerFavoritos().filterNot { it.getInt("dni") == dni }
        guardarLista(lista)
    }

    fun esFavorito(dni: Int): Boolean {
        return obtenerFavoritos().any { it.getInt("dni") == dni }
    }

    fun obtenerFavoritos(): List<JSONObject> {
        val json = prefs.getString("favoritos", "[]")
        val array = JSONArray(json)
        val lista = mutableListOf<JSONObject>()
        for (i in 0 until array.length()) {
            lista.add(array.getJSONObject(i))
        }
        return lista
    }

    private fun guardarLista(lista: List<JSONObject>) {
        val array = JSONArray()
        lista.forEach { array.put(it) }
        prefs.edit().putString("favoritos", array.toString()).apply()
    }
}
