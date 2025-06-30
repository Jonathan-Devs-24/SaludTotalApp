package com.example.saludtotalapp.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import com.example.saludtotalapp.Model.Usuario


interface ApiService {
    @GET("usuarios")
    fun getUsuarios(): Call<List<Usuario>>
}
