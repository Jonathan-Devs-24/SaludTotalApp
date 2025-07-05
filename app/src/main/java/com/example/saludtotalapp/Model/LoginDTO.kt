package com.example.saludtotalapp.Model

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("Correo") val correo: String,
    @SerializedName("Contrasenia") val contrasenia: String
)