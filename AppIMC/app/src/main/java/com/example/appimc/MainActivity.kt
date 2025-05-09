package com.example.appimc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var inputLayoutPeso: TextInputLayout
    lateinit var textInputPeso: TextInputEditText
    lateinit var inputLayoutAltura: TextInputLayout
    lateinit var textInputAltura: TextInputEditText
    lateinit var buttonCalcularImc: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarComponentes()

        buttonCalcularImc.setOnClickListener {
            if (validarCampos()) {
                var peso = textInputPeso.text.toString().toDouble()
                var altura = textInputAltura.text.toString().toDouble()
                var statusIMC = calcularStatusIMC(peso, altura)

                var intent = Intent(this, ImcCalculadoActivity::class.java)
                intent.putExtra("imc", Imc(peso, altura, statusIMC))

                startActivity(intent)
            }
        }
    }

    private fun inicializarComponentes() {
        inputLayoutPeso = findViewById<TextInputLayout>(R.id.input_layout_peso)
        textInputPeso = findViewById<TextInputEditText>(R.id.text_input_peso)
        inputLayoutAltura = findViewById<TextInputLayout>(R.id.input_layout_altura)
        textInputAltura = findViewById<TextInputEditText>(R.id.text_input_altura)
        buttonCalcularImc = findViewById<Button>(R.id.button_calcular_imc)
    }

    private fun validarCampos(): Boolean {
        var tudoOK = true;

        inputLayoutPeso.error = ""
        inputLayoutAltura.error = ""

        if (textInputPeso.text?.isEmpty() ?: true) {
            tudoOK = false
            inputLayoutPeso.error = "É necessário informar o peso!"
        }

        if (textInputAltura.text?.isEmpty() ?: true) {
            tudoOK = false
            inputLayoutAltura.error = "É necessário informar a altura!"
        }

        return tudoOK;
    }

    private fun calcularStatusIMC(peso: Double, altura: Double): String {
        val imc = peso / (altura * altura)

        return when {
            imc < 18.5 -> "Baixo"
            imc < 25.0 -> "Normal"
            imc < 30.0 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }

    override fun onResume() {
        super.onResume()

        textInputPeso.text = null
        textInputAltura.text = null

        textInputPeso.clearFocus()
        textInputAltura.clearFocus()
    }
}