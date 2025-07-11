package com.example.saludtotalapp.model

import java.io.Serializable

data class Usuario(
    val nombre : String,
    val apellido : String,
    val dni : Int,
    val correo : String,
    val telefono : Long?,
    val contrasenia : String,
    val rol : String
) : Serializable
