package com.example.praticandokotlin

const val teste = "";


interface Usuario{
    var nome: String
}

class Pedro(override var nome: String = "Pedro") : Usuario{
}


fun main(){

    val usuario = Pedro()
    println(usuario.nome)
}