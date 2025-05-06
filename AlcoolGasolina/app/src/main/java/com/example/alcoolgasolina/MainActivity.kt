package com.example.alcoolgasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var textResultado: TextView
    private lateinit var buttonCalcular: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarComponentesInterface();

        buttonCalcular.setOnClickListener {
            calcularMelhorPreco()
        }

    }


    private fun inicializarComponentesInterface() {
        textInputAlcool = findViewById<TextInputLayout>(R.id.text_input_alcool)
        editAlcool = findViewById<TextInputEditText>(R.id.edit_alcool)

        textInputGasolina = findViewById<TextInputLayout>(R.id.text_input_gasolina)
        editGasolina = findViewById<TextInputEditText>(R.id.edit_gasolina)

        textResultado = findViewById<TextView>(R.id.text_resultado)
        buttonCalcular = findViewById<Button>(R.id.button_calcular)
    }

    private fun calcularMelhorPreco() {
        var precoAlcool = editAlcool.text.toString()
        var precoGasolina = editGasolina.text.toString()

        if (validarCampos(precoAlcool, precoGasolina)) {

            var resultado = precoAlcool.toDouble() / precoGasolina.toDouble()

            if(resultado >= 0.7){
                textResultado.text = "Melhor utilizar Gasolina!"
            } else {
                textResultado.text = "Melhor utilizar Álcool!"
            }

        }

    }

    private fun validarCampos(precoAlcool: String, precoGasolina: String): Boolean {

        textInputAlcool.error = ""
        textInputGasolina.error = ""

        if (precoAlcool.isEmpty()) {
            textInputAlcool.error = "Digite o preço do álcool!"
            return false
        }

        if (precoGasolina.isEmpty()) {
            textInputGasolina.error = "Digite o preço da gasolina!"
            return false
        }

        return true
    }
}