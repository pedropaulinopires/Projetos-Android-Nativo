package com.example.aulafragment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.aulafragment.fragments.ChamadasFragment
import com.example.aulafragment.fragments.ConversasFragment

class MainActivity : AppCompatActivity() {

    lateinit var buttonConversas: Button
    lateinit var buttonChamadas: Button
//    var conversasFragments = ConversasFragment()
//    var chamadasFragment = ChamadasFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarComponentes()

//        var fragmentManager = supportFragmentManager.beginTransaction()
//        fragmentManager.add(R.id.fragment_conteudo, ConversasFragment())
//        fragmentManager.commit()

        criarFragmentConversas()

        buttonConversas.setOnClickListener { criarFragmentConversas() }
        buttonChamadas.setOnClickListener { criarFragmentChamadas() }

    }

    fun iniciarComponentes(){
        buttonConversas = findViewById<Button>(R.id.button_conversas)
        buttonChamadas = findViewById<Button>(R.id.button_chamadas)
    }

    fun criarFragmentConversas(){
        supportFragmentManager
            .beginTransaction()
//            .remove(chamadasFragment)
            .replace(R.id.fragment_conteudo, ConversasFragment())
            .commit()

    }

    fun criarFragmentChamadas(){
        supportFragmentManager.commit {
            replace(R.id.fragment_conteudo, ChamadasFragment().apply {
                arguments = bundleOf(
                    "texto" to "Chamadas"
                )
            })
        }

    }
}