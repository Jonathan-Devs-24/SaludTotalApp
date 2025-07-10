package com.example.saludtotalapp.network

import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.Call
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


    // Paciente por ID
    @GET("paciente/{id}")
    fun getPaciente(@retrofit2.http.Path("id") id: Int): Call<Paciente>

    // Actualiza info del paciente
    @PUT("paciente/{id}")
    fun actualizarPaciente(@retrofit2.http.Path("id") id: Int, @Body paciente: Paciente): Call<Void>


    // Elimina paciente
    @DELETE("paciente/{id}")
    fun eliminarPaciente(@retrofit2.http.Path("id") id: Int): Call<Void>

}



