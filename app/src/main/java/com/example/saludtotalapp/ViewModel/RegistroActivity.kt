// En RegistroActivity.kt
package com.example.saludtotalapp.ViewModel // o el paquete real que estés usando

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saludtotalapp.R

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro) // o el nombre correcto del layout
    }
}

