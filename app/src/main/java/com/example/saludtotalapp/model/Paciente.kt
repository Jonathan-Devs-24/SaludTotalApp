package com.example.saludtotalapp.model

data class Paciente(
    val idPaciente : Int,
    val numeroAfliado : String,
    val observacion : String?,
    val usuario : Usuario
)
