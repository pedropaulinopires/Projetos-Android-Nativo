package com.example.aulafragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.aulafragment.R

class ChamadasFragment : Fragment() {

    private var texto: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        texto = arguments?.getString("texto")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(
            R.layout.fragment_chamadas,
            container,
            false)

        var textResultado = view.findViewById<TextView>(R.id.text_resultado)
        textResultado.setText(texto ?: "Sem texto")

        return view
    }
}