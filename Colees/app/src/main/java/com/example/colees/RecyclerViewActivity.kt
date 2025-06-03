package com.example.colees

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var rvLista: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var lista = listOf<String>("Pedro", "Ana", "Julio")

        rvLista = findViewById<RecyclerView>(R.id.rv_lista)
        rvLista.adapter = MensagemAdapter(lista)
//        rvLista.layoutManager = LinearLayoutManager(this)
//        rvLista.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        rvLista.layoutManager = GridLayoutManager(this, 2)
        rvLista.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

//        rvLista.addItemDecoration(
//            DividerItemDecoration(
//                this,
//                RecyclerView.HORIZONTAL
//            )
//        )
    }
}