package com.example.saludtotalapp.model

import java.io.Serializable

data class Paciente(
    val idPaciente : Int,
    val numeroAfliado : String,
    val observacion : String?,
    val usuario : Usuario
) : Serializable
