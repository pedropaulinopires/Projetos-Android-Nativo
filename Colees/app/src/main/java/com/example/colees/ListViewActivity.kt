package com.example.colees

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListViewActivity : AppCompatActivity() {

    lateinit var listUsuarios: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var listaUsuarios = listOf<String>("Pedro", "Ana", "José", "Marcelo", "Angélica")

        listUsuarios = findViewById<ListView>(R.id.list_usuarios)
        listUsuarios.adapter = ArrayAdapter(this,
                                            android.R.layout.simple_list_item_1,
                                            android.R.id.text1,
                                            listaUsuarios)
    }
}