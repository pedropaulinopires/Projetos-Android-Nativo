package com.example.colees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MensagemAdapter(
) : RecyclerView.Adapter<MensagemAdapter.MensagemViewHolder>() {

    private val lista = mutableListOf<String>()


    fun atualizarListaDados(listaNova: List<String>){
        this.lista.addAll(listaNova)
        notifyDataSetChanged()
    }


    inner class MensagemViewHolder(
        val itemView: View
    ) : RecyclerView.ViewHolder(itemView){
        val textNome: TextView

        init {
            textNome = itemView.findViewById<TextView>(R.id.text_nome_card)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MensagemViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.item_cardview, parent, false)
        return  MensagemViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MensagemViewHolder,
        position: Int
    ) {
        var nome = lista[position]
        holder.textNome.text = nome
    }

    override fun getItemCount(): Int = lista.size
}