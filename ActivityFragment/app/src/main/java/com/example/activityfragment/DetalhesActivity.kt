package com.example.activityfragment

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalhesActivity : AppCompatActivity() {

    lateinit var buttonFechar: Button
    lateinit var textFilme: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textFilme = findViewById<TextView>(R.id.text_detalhes)
        var bundle = intent.extras

        var filme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelable("filme", Filme::class.java) ?: Filme("Velozes e furiosos")
        } else {
            bundle?.getParcelable<Filme>("filme") as Filme ?: Filme("Velozes e furiosos")
        }

        // usando serialize
//        var filme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            bundle?.getSerializable<Filme>("filme", Filme::class.java) ?: Filme()
//        } else {
//            bundle?.getSerializable("filme") as Filme ?: Filme()
//        }

        textFilme.text = filme.nome
        buttonFechar = findViewById<Button>(R.id.button_fechar)
        buttonFechar.setOnClickListener {
            finish()
        }
    }
}