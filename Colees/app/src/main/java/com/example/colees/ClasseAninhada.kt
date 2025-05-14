package com.example.colees

class ClasseAninhada {

}


class Motorista(val nome: String){

    inner class Caminhao(val modelo: String){
        fun exibirDados() = println("Modelo: $modelo - Motorista $nome")
    }
}

fun main() {
    val motorista = Motorista("Pedro")
    var caminhao = motorista.Caminhao("F250")
    caminhao.exibirDados()
}

