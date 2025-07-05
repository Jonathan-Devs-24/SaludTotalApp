package com.example.saludtotalapp.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import com.example.saludtotalapp.Model.Usuario
import com.example.saludtotalapp.Model.LoginDTO
import com.example.saludtotalapp.Model.LoginResponse


interface ApiService {

    // Accede a todos los usuarios, ¿Para que? no sé
    @GET("usuario/todos")
    fun getUsuarios(): Call<List<Usuario>>

    // Registro
    @POST("usuario/register")
    fun registrarUsuario(@Body usuario: Usuario): Call<Void>

    // Login
    @POST("Usuario/login")
    fun login(@Body loginDTO: LoginDTO): Call<LoginResponse>


}



