package com.example.saludtotalapp.network

import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import com.example.saludtotalapp.model.Usuario
import com.example.saludtotalapp.model.LoginDTO
import com.example.saludtotalapp.model.LoginResponse
import com.example.saludtotalapp.model.Paciente


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

    // Accede a los pacientes
    @GET("paciente")
    fun getPacientes(): Call<List<Paciente>>

}



