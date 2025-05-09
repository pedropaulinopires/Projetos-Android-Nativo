package com.example.appimc

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ImcCalculadoActivity : AppCompatActivity() {

    lateinit var textPeso: TextView
    lateinit var textAltura: TextView
    lateinit var textResultado: TextView
    lateinit var buttonRefazer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarComponentes()

        buttonRefazer.setOnClickListener {
            finish()
        }

        var bundle = intent.extras
        var imc = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelable<Imc>("imc", Imc::class.java)
        } else {
            bundle?.getParcelable<Imc>("imc")
        }

        textPeso.text = "Peso informado ${imc?.peso} kg"
        textAltura.text = "Altura informada ${imc?.altura} m"
        textResultado.text = imc?.resultado
    }

    private fun inicializarComponentes() {
        textPeso = findViewById<TextView>(R.id.text_peso)
        textAltura = findViewById<TextView>(R.id.text_altura)
        textResultado = findViewById<TextView>(R.id.text_resultado)
        buttonRefazer = findViewById<Button>(R.id.button_refazer)
    }
}