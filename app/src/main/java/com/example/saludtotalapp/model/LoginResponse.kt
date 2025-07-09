package com.example.saludtotalapp.model

data class LoginResponse(
    val idUsuario: Int,
    val nombre: String,
    val apellido: String,
    val rol: String
)